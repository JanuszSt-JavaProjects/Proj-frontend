package library.frontend.view.admin.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import library.backend.library.domain.Copy;
import library.backend.library.domain.Status;
import library.backend.library.service.BookService;
import library.backend.library.service.CopyService;
import library.frontend.view.admin.view.AdmCopyView;
import org.springframework.context.ApplicationContext;


public class CopyForm extends FormLayout {

    AdmCopyView admCopyView;
    ApplicationContext context;
    CopyService copyService;
    BookService bookService;


    private TextField book_title = new TextField("book title");
    private TextField signature = new TextField("signature");
    private IntegerField bookId =new IntegerField("book Id");
    ComboBox<Status> status = new ComboBox<>("Status");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");
    HorizontalLayout buttons = new HorizontalLayout(modify, save, delete);


    private final Binder<Copy> binder = new Binder<>(Copy.class);


    public CopyForm(AdmCopyView admCopyView, ApplicationContext context) {

        this.admCopyView = admCopyView;
        this.context = context;
        copyService = context.getBean(CopyService.class);
        bookService =context.getBean(BookService.class);

        status.setItems(Status.AVAILABLE, Status.IN_USE, Status.DESTROYED, Status.LOST);
        book_title.setEnabled(false);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.setVisible(false);
        modify.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        modify.setVisible(false);

        binder.bindInstanceFields(this);

        save.addClickListener(click -> save());
        modify.addClickListener(click -> update());
        delete.addClickListener(click -> delete());

        VerticalLayout layout_Right =
                new VerticalLayout(
                        bookId,
                        book_title,
                        signature, status, buttons);

        add(layout_Right);
    }


    public void setSaveAction() {
        setEnabled(true);
        setCopy(new Copy());
        modify.setVisible(false);
        save.setVisible(true);
    }

    public void setUpdateAction() {
        setEnabled(true);
        save.setVisible(false);
        modify.setVisible(true);
    }

    public void setCopy(Copy copy) {

        binder.setBean(copy);

    }


    private void save() {
        Copy copy = binder.getBean();
        copy.setBook(bookService.getOne(bookId.getValue()));
        copyService.save(copy);
        admCopyView.refresh();
        setCopy(copy);
        setDisable();
    }

    private void update() {

        Copy copy = binder.getBean();
        copyService.update(copy);
        admCopyView.refresh();
        setCopy(copy);
        setDisable();
    }

    private void delete() {
        Copy copy = binder.getBean();
        long id = copy.getId();
        copyService.delete(id);
        setCopy(copy);
        admCopyView.refresh();
    }

    void setDisable() {
        setEnabled(false);
    }
}

