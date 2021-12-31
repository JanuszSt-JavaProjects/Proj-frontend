package library.frontend.view.admin.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import library.backend.library.domain.Customer;
import library.backend.library.service.CustomerService;
import library.frontend.view.admin.view.AdmCustomView;
import org.springframework.context.ApplicationContext;


public class CustomerForm extends FormLayout {

    AdmCustomView admCustomView;
    ApplicationContext context;
    CustomerService service;


    private TextField firstname = new TextField("Firstname");
    private TextField lastname = new TextField("Lastname");

    private final Button add = new Button("Add");


    private final Binder<Customer> binder = new Binder<>(Customer.class);


    public CustomerForm(AdmCustomView admCustomView, ApplicationContext context) {
        this.admCustomView = admCustomView;
        this.context = context;
        service = context.getBean(CustomerService.class);


        Button save = new Button("Save");
        Button delete = new Button("Delete");
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        binder.bindInstanceFields(this);


        VerticalLayout layout_Right =
                new VerticalLayout(firstname, lastname, buttons);

        add(layout_Right);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

    }

    public void setCustomer(Customer customer) {
        binder.setBean(customer);

        if (customer == null) {
            setVisible(false);
        } else {
            setVisible(true);
            firstname.focus();
        }
    }


    private void save() {
        Customer customer = binder.getBean();

        service.save(customer);


        admCustomView.refresh();
        setCustomer(customer);
    }

    private void delete() {
        Customer customer = binder.getBean();
        service.remove(customer.getId());
        admCustomView.refresh();
        setCustomer(customer);
    }

}
