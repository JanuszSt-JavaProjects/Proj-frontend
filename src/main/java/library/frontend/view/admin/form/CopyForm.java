package library.frontend.view.admin.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import library.backend.library.domain.Copy;
import library.backend.library.domain.Status;
import library.backend.library.service.CopyService;
import library.frontend.view.admin.view.AdmCopyView;
import org.springframework.context.ApplicationContext;


public class CopyForm extends FormLayout {

    AdmCopyView admCopyView;
    ApplicationContext context;
    CopyService service;


    private TextField book_title = new TextField("book title");
    private TextField signature = new TextField("signature");
    ComboBox<Status> status = new ComboBox<>("Status");


    private final Button add = new Button("Add");


    private final Binder<Copy> binder = new Binder<>(Copy.class);


    public CopyForm(AdmCopyView admCopyView, ApplicationContext context) {
        this.admCopyView = admCopyView;
        this.context = context;
        service = context.getBean(CopyService.class);

        status.setItems(Status.AVAILABLE,Status.IN_USE,Status.DESTROYED,Status.LOST);

        Button save = new Button("Save");
        Button delete = new Button("Delete");
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        binder.bindInstanceFields(this);


        VerticalLayout layout_Right =
                new VerticalLayout(
                        book_title,
                        signature, status, buttons);

        add(layout_Right);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

    }

    public void setCopy(Copy copy) {
        binder.setBean(copy);

        if (copy == null) {
            setVisible(false);
        } else {
            book_title.setValue(copy.getBook().getTitle());
            setVisible(true);
        }
    }


    private void save() {
        Copy copy = binder.getBean();
        service.save(copy);
        admCopyView.refresh();
        setCopy(copy);
    }

    private void delete() {
        Copy copy = binder.getBean();
        long id =copy.getId();
        service.delete( id);
        admCopyView.refresh();
        setCopy(copy);
    }

}

