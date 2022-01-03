package library.view.admin.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import library.dto.BorrowDto;
import library.dto.ConvertedBorrowDto;
import library.service.BorrowService;
import library.service.converter.BorrowConverter;
import library.view.admin.view.AdmBorrowView;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

public class BorrowForm extends FormLayout {

    AdmBorrowView admBorrowView;
    ApplicationContext context;
    BorrowService borrowService;
    BorrowConverter borrowConverter;


    private IntegerField id = new IntegerField("Borrow Id");
    private IntegerField bookId = new IntegerField("Book Id");

    private IntegerField copyId = new IntegerField("Copy Id");
    private IntegerField clientId = new IntegerField("Customer Id");


    private TextField borrowDate = new TextField("Borrow date");
    private TextField returnDate = new TextField("Return date");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");
    HorizontalLayout buttons = new HorizontalLayout(modify, save, delete);

    private Binder<ConvertedBorrowDto> binder = new Binder<>(ConvertedBorrowDto.class);


    public BorrowForm(AdmBorrowView admBorrowView,
                      ApplicationContext context) {

        binder.bindInstanceFields(this);

        this.admBorrowView = admBorrowView;
        this.context = context;
        borrowService = context.getBean(BorrowService.class);
        borrowConverter = context.getBean(BorrowConverter.class);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.setVisible(false);
        modify.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        modify.setVisible(false);


        save.addClickListener(click -> save());
        modify.addClickListener(click -> update());
        delete.addClickListener(click -> delete());


        VerticalLayout layout_Right =
                new VerticalLayout(new HorizontalLayout(
                        id,
                        bookId,
                        copyId),
                        clientId,
                        borrowDate,
                        returnDate,
                        buttons);

        add(layout_Right);
    }


    public void setSaveAction() {
        setEnabled(true);
        ConvertedBorrowDto convertedBorrowDto = new ConvertedBorrowDto();
        convertedBorrowDto.setBorrowDate(LocalDate.now().toString());
        convertedBorrowDto.setReturnDate(LocalDate.MAX.toString());
        setBorrow(convertedBorrowDto);

        borrowDate.setEnabled(false);
        returnDate.setVisible(false);

        modify.setVisible(false);
        save.setVisible(true);
        id.setEnabled(false);
        borrowDate.setEnabled(false);
    }

    public void setUpdateAction() {
        setEnabled(true);
        save.setVisible(false);
        modify.setVisible(true);

        returnDate.setVisible(true);

        id.setEnabled(false);
        borrowDate.setEnabled(true);
        returnDate.setVisible(true);
    }


    public void setBorrow(ConvertedBorrowDto borrow) {
        binder.setBean(borrow);
    }


    private void save() {

        ConvertedBorrowDto convertedBorrowDto = binder.getBean();

        BorrowDto borrowDto = borrowConverter.convertToBorrowDto(convertedBorrowDto);

        borrowService.save(borrowDto);
        admBorrowView.refresh();
        setBorrow(convertedBorrowDto);
        setDisable();
    }

    private void update() {

        ConvertedBorrowDto convertedBorrowDto = binder.getBean();

        BorrowDto borrowDto = borrowConverter.convertToBorrowDto(convertedBorrowDto);

        borrowService.update(borrowDto);
        admBorrowView.refresh();
        setBorrow(convertedBorrowDto);
        setDisable();
    }

    private void delete() {

        ConvertedBorrowDto convertedBorrowDto = binder.getBean();
        BorrowDto borrowDto = borrowConverter.convertToBorrowDto(convertedBorrowDto);
        long id = borrowDto.getId();
        borrowService.delete(id);
        admBorrowView.refresh();
        setBorrow(convertedBorrowDto);
        setDisable();
    }

    void setDisable() {
        setEnabled(false);
    }

}


