package com.example.application.component;

import com.example.application.db.Todo;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;

import java.util.HashMap;
import java.util.Map;

@Tag("todo-view")
@JsModule("views/todo/todo-view.ts")
public class TodoComponent extends Component {

    private static PropertyDescriptor<String, String>
            MESSAGE = PropertyDescriptors
            .propertyWithDefault("message", "");

    public TodoComponent(){
        set(MESSAGE, "booo");
    }
    public TodoComponent(String message) {
        set(MESSAGE, message);
    }

    public String getMessage() {
        return get(MESSAGE);
    }

    public void setMessage(String message) {
        set(MESSAGE, message);
    }


    public void setTodo(Todo todo) {
        Map<String, Object> map = new HashMap<>();
        map.put("id",todo.getId());
        map.put("done",todo.isDone());
        map.put("task",todo.getTask());
        super.getElement().setPropertyMap("todo",map);
    }


}
