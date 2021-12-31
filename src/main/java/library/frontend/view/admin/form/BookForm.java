package library.frontend.view.admin.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import library.backend.library.domain.Book;
import library.backend.library.service.BookService;
import library.frontend.view.admin.AdmBookView;
import org.springframework.context.ApplicationContext;


public class BookForm extends FormLayout {

    AdmBookView admBookView;
    ApplicationContext context;
    BookService bookService;

    private  TextField title = new TextField("Title");
    TextField author = new TextField("Author");
    IntegerField releaseDate = new IntegerField("Release year");

    private final Button add = new Button("Add");


    private final Binder<Book> binder = new Binder<>(Book.class);


    public BookForm(AdmBookView admBookView, ApplicationContext context) {
        this.admBookView = admBookView;
        this.context = context;
        bookService = context.getBean(BookService.class);


        Button save = new Button("Save");
        Button delete = new Button("Delete");
        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        binder.bindInstanceFields(this);


        VerticalLayout layout_Right =
                new VerticalLayout(title, author, releaseDate, buttons);

        add(layout_Right);

        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());

    }

    public void setBook(Book book) {
        binder.setBean(book);

        if (book == null) {
            setVisible(false);
        } else {
            setVisible(true);
            title.focus();
        }
    }


    private void save() {
        Book book = binder.getBean();

        bookService.save(book);


        admBookView.refresh();
        setBook(book);
    }

    private void delete() {
        Book book = binder.getBean();
        bookService.delete(book.getId());
        admBookView.refresh();
        setBook(book);
    }

}
