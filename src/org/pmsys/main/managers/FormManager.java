package org.pmsys.main.managers;

import org.pmsys.main.actions.Actions;
import org.pmsys.main.ui.forms.FormType;
import org.pmsys.main.entities.request.Request;
import org.pmsys.main.ui.forms.AbstractSimpleForm;
import org.pmsys.main.ui.forms.ProjectSimpleForm;
import org.pmsys.main.ui.forms.TaskSimpleForm;

import java.util.EnumMap;
import java.util.Map;

// I mean it works but it should be refactored
public enum FormManager {

    INSTANCE;

    private final Map<FormType, AbstractSimpleForm> formMap = new EnumMap<>(FormType.class);

    FormManager() {

    }

    public void showForm(FormType formType, Object data) {
        AbstractSimpleForm formUI = formMap.get(formType);
        if (formUI == null) {
            throw new IllegalArgumentException("Form type not registered: " + formType);
        }
        if (data != null) {
            formUI.setFormData(data);
            formUI.setFormTitle(formType == FormType.PROJECT ? "Project Edit" : "Task Edit");
            formUI.setAction(formType == FormType.PROJECT ? Actions.EDIT_PROJECT : Actions.EDIT_TASK);
            formUI.setButtonText("Update");
        } else {
            formUI.clearFields();
            formUI.setFormTitle(formType == FormType.PROJECT ? "Project Create" : "Task Create");
            formUI.setAction(formType == FormType.PROJECT ? Actions.ADD_PROJECT : Actions.ADD_TASK);
            formUI.setButtonText("Create");
        }
        formUI.showForm();
    }

    public void showForm(FormType formType) {
        showForm(formType, null);
    }

    public Request getFormData(FormType formType, String id) {
        AbstractSimpleForm formUI = formMap.get(formType);
        if (formUI == null) {
            throw new IllegalArgumentException("Form type not registered: " + formType);
        }
        return formUI.getFormData(id);
    }

    public Request getFormData(FormType formType) {
        AbstractSimpleForm formUI = formMap.get(formType);
        if (formUI == null) {
            throw new IllegalArgumentException("Form type not registered: " + formType);
        }
        return formUI.getFormData();
    }

    public AbstractSimpleForm getForm(FormType formType) {
        return formMap.get(formType);
    }

    public void hideForm(FormType formType) {
        AbstractSimpleForm formUI = formMap.get(formType);
        if (formUI != null) {
            formUI.dispose();
        } else {
            throw new IllegalArgumentException("Form type not registered: " + formType);
        }
    }

    public void loadForms() {
        formMap.put(FormType.PROJECT, new ProjectSimpleForm());
        formMap.put(FormType.TASK, new TaskSimpleForm());
    }
}
