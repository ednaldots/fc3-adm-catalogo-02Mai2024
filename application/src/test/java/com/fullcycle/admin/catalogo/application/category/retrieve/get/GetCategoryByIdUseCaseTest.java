package com.fullcycle.admin.catalogo.application.category.retrieve.get;

import com.fullcycle.admin.catalogo.domain.category.Category;
import com.fullcycle.admin.catalogo.domain.category.CategoryGateway;
import com.fullcycle.admin.catalogo.domain.category.CategoryID;
import com.fullcycle.admin.catalogo.domain.exception.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetCategoryByIdUseCaseTest {

    private DefaultGetCategoryByIdUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;

    @BeforeEach
    void setUp() {
        // Inicializa o useCase com o mock de CategoryGateway
        useCase = new DefaultGetCategoryByIdUseCase(categoryGateway);
        // Reseta o mock antes de cada teste
        Mockito.reset(categoryGateway);
    }

    @Test
    public void givenAValidId_whenCallsGetCategory_shouldReturnCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);
        final var expectedId = aCategory.getId();

        // Configura o comportamento do mock
        when(categoryGateway.findByID(eq(expectedId)))
                .thenReturn(Optional.of(aCategory.clone()));

        // Executa o caso de uso
        final var actualCategory = useCase.execute(expectedId.getValue());

        // Verifica se os resultados são os esperados
        // Verifica se os resultados são os esperados
        assertEquals(expectedId.getValue(), actualCategory.id().getValue());
        assertEquals(expectedName, actualCategory.name());
        assertEquals(expectedDescription, actualCategory.description());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertEquals(aCategory.getCreatedAt(), actualCategory.createdAt());
        assertEquals(aCategory.getUpdatedAt(), actualCategory.updatedAt());
        assertEquals(aCategory.getDeletedAt(), actualCategory.deletedAt());
    }

    @Test
    public void  givenAInvalidId_whenCallsGetCategory_shouldReturnNotFound () {
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedId = CategoryID.from("123");

        when(categoryGateway.findByID(eq(expectedId))).thenReturn(Optional.empty());

        final var actualException = Assertions.assertThrows(DomainException.class, () -> useCase.execute(expectedId.getValue()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAValidId_whenGatewayThrowsException_shouldReturnException() {
        final var expectedErrorMessage = "Gateway error";
        final var expectedId = CategoryID.from("123");

        // Configura o comportamento do mock
        when(categoryGateway.findByID(eq(expectedId)))
                .thenThrow(new IllegalStateException(expectedErrorMessage));

        // Executa o caso de uso e captura a exceção lançada
        final var actualException = assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(expectedId.getValue())
        );

        // Verifica se a mensagem de erro é a esperada
        assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
