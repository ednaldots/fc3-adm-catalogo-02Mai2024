package com.fullcycle.admin.catalogo.application.category.create;

import com.fullcycle.admin.catalogo.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
    extends useCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>>{


    public abstract
    Either <Notification, CreateCategoryOutput> execute(CreateCategoryCommand aCommand);
}

