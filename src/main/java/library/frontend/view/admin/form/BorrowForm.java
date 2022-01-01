package library.frontend.view.admin.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import library.backend.library.domain.Borrow;
import library.backend.library.service.BorrowService;
import library.frontend.view.admin.view.AdmBorrowView;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

public class BorrowForm extends FormLayout {

    AdmBorrowView admBorrowView;
    ApplicationContext context;
    BorrowService service;

    private IntegerField int_BookId = new IntegerField("book Id");
    private IntegerField int_CopyId = new IntegerField("copy Id");
    private IntegerField int_CustomerId = new IntegerField("customer Id");

    private TextField any_BorrowDate = new TextField("borrow date");
    private TextField any_ReturnDate = new TextField("return date");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    private final Button add = new Button("Add");

    private final Binder<Borrow> binder = new Binder<>(Borrow.class);


    public BorrowForm(AdmBorrowView admBorrowView, ApplicationContext context) {
        this.admBorrowView = admBorrowView;
        this.context = context;
        service = context.getBean(BorrowService.class);


        HorizontalLayout buttons = new HorizontalLayout(save, delete);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);


        VerticalLayout layout_Right =
                new VerticalLayout(new HorizontalLayout(int_BookId,
                        int_CopyId),

                        int_CustomerId,
                        any_BorrowDate,
                        any_ReturnDate,
                        buttons);

        add(layout_Right);

        save.setEnabled(false);
        save.addClickListener(event -> save());
        delete.addClickListener(event -> delete());
    }


    public void setSaveEnable() {
        save.setEnabled(true);
    }

    public void setSaveDisable() {
        save.setEnabled(false);
    }


    public void setBorrow(Borrow borrow) {

        binder.setBean(borrow);
        save.setEnabled(true);


        if (borrow == null) {
            setVisible(false);
        }else if (borrow.getBorrowDate().equals(LocalDate.MIN)) {
            int_BookId.setValue(0);
            int_CopyId.setValue(0);
            int_CustomerId.setValue(0);
            any_BorrowDate.setValue(LocalDate.now().toString());
        } else {


            int_CustomerId.setValue((int) borrow.getCustomer().getId());
            int_BookId.setValue((int) borrow.getBookId());
            int_CopyId.setValue((int) borrow.getCopyId());
            any_BorrowDate.setValue(borrow.getBorrowDate().toString());
            any_ReturnDate.setValue(
                    borrow.getReturnDate() == null ? "" : borrow.getReturnDate().toString()
            );
            setVisible(true);
            try {
                binder.bindInstanceFields(this);
            } catch (RuntimeException e) {

                /*
                 *  ADD LOGGER -> database
                 * */
            }
        }
    }


    private void save() {
        Borrow borrow = binder.getBean();
        service.save(borrow);
        admBorrowView.refresh();
        setBorrow(borrow);
        save.setEnabled(false);
    }

    private void delete() {
        Borrow borrow = binder.getBean();
        long id = borrow.getId();
        service.remove(id);
        admBorrowView.refresh();
        setBorrow(borrow);
    }

}


