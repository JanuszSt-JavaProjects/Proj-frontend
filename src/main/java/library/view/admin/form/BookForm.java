package library.view.admin.form;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import library.dto.BookDto;
import library.service.BookService;
import library.view.admin.view.AdmBookView;
import org.springframework.context.ApplicationContext;


public class BookForm extends FormLayout {

    AdmBookView admBookView;
    ApplicationContext context;
    BookService service;

    private TextField title = new TextField("Title");
    private TextField author = new TextField("Author");
    private IntegerField releaseDate = new IntegerField("Release year");


    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");

    HorizontalLayout buttons = new HorizontalLayout(modify, save, delete);

    private final Binder<BookDto> binder = new Binder<>(BookDto.class);


    public BookForm(AdmBookView admBookView, ApplicationContext context) {
        this.admBookView = admBookView;
        this.context = context;
        service = context.getBean(BookService.class);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.setVisible(false);
        modify.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        modify.setVisible(false);

        binder.bindInstanceFields(this);


        save.addClickListener(click -> {
            try {
                save();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        modify.addClickListener(click -> update());
        delete.addClickListener(click -> delete());

        VerticalLayout layout_Right =
                new VerticalLayout(title, author, releaseDate, buttons);

        add(layout_Right);
    }


    public void setSaveAction() {
        setEnabled(true);
        setBook(new BookDto());
        modify.setVisible(false);
        save.setVisible(true);
        save.setEnabled(true);
    }

    public void setUpdateAction() {
        setEnabled(true);
        save.setVisible(false);
        modify.setVisible(true);
    }


    public void setBook(BookDto bookDto) {

        binder.setBean(bookDto);

        if (bookDto == null) {
            setVisible(false);
        } else {
            setVisible(true);
            title.focus();
        }
    }


    private void save() throws JsonProcessingException {

        BookDto book = binder.getBean();
        service.save(book);
        admBookView.refresh();
        setBook(book);
        setDisable();

    }

    private void update() {

        BookDto book = binder.getBean();
        service.update(book);
        admBookView.refresh();
        setBook(book);
        setDisable();
    }


    private void delete() {
        BookDto book = binder.getBean();
        service.delete(book.getId());
        admBookView.refresh();
        setBook(book);
        setDisable();
    }


    void setDisable() {
        setEnabled(false);
    }
}
