package com.example.application.views;

import com.example.application.component.TodoComponent;
import com.example.application.db.Todo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("hello-view")
public class HelloWorld extends VerticalLayout {
    public HelloWorld() {
        TodoComponent toDo = new TodoComponent("form hello word");
        TextField textField = new TextField("task");
        add(toDo,textField,new Button("change",(event)->{
            Todo todo = new Todo();
            todo.setId(1L);
            todo.setTask(textField.getValue());
            todo.setDone(true);
            toDo.setMessage("has change");
            toDo.setTodo(todo);
        }));
    }
}