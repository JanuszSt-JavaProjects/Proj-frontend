package library.view.admin.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import library.dto.CustomerDto;
import library.service.CustomerService;
import library.view.admin.view.AdmCustomerView;
import org.springframework.context.ApplicationContext;


public class CustomerForm extends FormLayout {

    AdmCustomerView admCustomerView;
    ApplicationContext context;
    CustomerService service;

    private TextField firstname = new TextField("Firstname");
    private TextField lastname = new TextField("Lastname");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");

    HorizontalLayout buttons = new HorizontalLayout(modify, save, delete);


    private final Binder<CustomerDto> binder = new Binder<>(CustomerDto.class);


    public CustomerForm(AdmCustomerView admCustomerView, ApplicationContext context) {
        this.admCustomerView = admCustomerView;
        this.context = context;
        service = context.getBean(CustomerService.class);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.setVisible(false);
        modify.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        modify.setVisible(false);

        binder.bindInstanceFields(this);

        save.addClickListener(click -> save());
        modify.addClickListener(click -> update());
        delete.addClickListener(click -> delete());


        VerticalLayout layout_Right =
                new VerticalLayout(firstname, lastname, buttons);

        add(layout_Right);
    }


    public void setSaveAction() {
        setEnabled(true);
        setCustomer(new CustomerDto());
        modify.setVisible(false);
        save.setVisible(true);
        save.setEnabled(true);
    }

    public void setUpdateAction() {
        setEnabled(true);
        save.setVisible(false);
        modify.setVisible(true);
    }

    public void setCustomer(CustomerDto customerDto) {

        binder.setBean(customerDto);
        if (customerDto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            firstname.focus();
        }
    }


    private void save() {
        CustomerDto customer = binder.getBean();

        service.save(customer);
        setCustomer(customer);
        admCustomerView.refresh();
        save.setEnabled(false);
    }

    private void update() {

        CustomerDto customer = binder.getBean();
        service.update(customer);
        admCustomerView.refresh();
        setCustomer(customer);
        setDisable();
    }

    private void delete() {
        CustomerDto customerDto = binder.getBean();
        service.remove(customerDto.getId());
        admCustomerView.refresh();
        setCustomer(customerDto);
        setDisable();
    }

    void setDisable() {
        setEnabled(false);
    }
}
