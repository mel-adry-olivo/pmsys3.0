package org.pmsys.main.manager;

import org.pmsys.constants.FormType;
import org.pmsys.main.model.request.Request;
import org.pmsys.main.ui.forms.AbstractForm;
import org.pmsys.main.ui.forms.ProjectForm;
import org.pmsys.main.ui.forms.TaskForm;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FormManager {

    private final Map<FormType, AbstractForm> formMap = new HashMap<>();

    public FormManager() {
        formMap.put(FormType.PROJECT, new ProjectForm());
        formMap.put(FormType.TASK, new TaskForm());
    }

    public void showForm(FormType formType, Object data) {
        AbstractForm formUI = formMap.get(formType);

        if(data != null) {
            formUI.setFormData(data);
            formUI.setFormTitle(formType == FormType.PROJECT ? "Project Edit" : "Task Edit");
            formUI.setButtonText("Update");
        } else {
            formUI.clearFields();
            formUI.setFormTitle(formType == FormType.PROJECT ? "Project Create" : "Task Create");
            formUI.setButtonText("Create");
        }

        formUI.showForm();
    }

    public Request getFormData(FormType formType, String id) {
        return formMap.get(formType).getFormData(id);
    }

    public Request getFormData(FormType formType) {
        return formMap.get(formType).getFormData();
    }

    public AbstractForm getFormUI(FormType formType) {
        return formMap.get(formType);
    }

    public void hideForm(FormType formType) {
        formMap.get(formType).dispose();
    }

    public void setFormActionHandler(FormType formType, ActionListener listener) {
        getFormUI(formType).handleActionButton(listener);
    }
}
