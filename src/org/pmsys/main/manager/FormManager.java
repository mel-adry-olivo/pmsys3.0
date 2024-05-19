package org.pmsys.main.manager;

import org.pmsys.constants.FormType;
import org.pmsys.main.model.request.Request;
import org.pmsys.main.ui.forms.AbstractFormUI;
import org.pmsys.main.ui.forms.ProjectFormUI;
import org.pmsys.main.ui.forms.TaskFormUI;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class FormManager {

    private final Map<FormType, AbstractFormUI> formMap = new HashMap<>();

    public FormManager() {
        formMap.put(FormType.PROJECT, new ProjectFormUI());
        formMap.put(FormType.TASK, new TaskFormUI());
    }

    public void showForm(FormType formType, Object data) {
        AbstractFormUI formUI = formMap.get(formType);

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

    public AbstractFormUI getFormUI(FormType formType) {
        return formMap.get(formType);
    }

    public void hideForm(FormType formType) {
        formMap.get(formType).dispose();
    }

    public void setFormActionHandler(FormType formType, ActionListener listener) {
        getFormUI(formType).handleActionButton(listener);
    }
}
