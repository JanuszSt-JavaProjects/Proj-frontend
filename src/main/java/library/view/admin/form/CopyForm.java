package library.view.admin.form;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

import library.dto.ConvertedCopyDto;
import library.dto.CopyDto;
import library.dto.StatusDto;
import library.service.BookService;
import library.service.converter.CopyConverter;
import library.service.CopyService;
import library.view.admin.view.AdmCopyView;
import org.springframework.context.ApplicationContext;

import static library.dto.StatusDto.*;

public class CopyForm extends FormLayout {

    AdmCopyView admCopyView;
    ApplicationContext context;
    CopyService copyService;
    BookService bookService;
    CopyConverter copyConverter;

    private IntegerField id =new IntegerField("id");
    private IntegerField bookId =new IntegerField("book Id");
    private TextField signature = new TextField("signature");
    ComboBox<StatusDto> statusDto = new ComboBox<>("Status");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button modify = new Button("Modify");
    HorizontalLayout buttons = new HorizontalLayout(modify, save, delete);


    private final Binder<ConvertedCopyDto> binder = new Binder<>(ConvertedCopyDto.class);


    public CopyForm(AdmCopyView admCopyView, ApplicationContext context) {

        binder.bindInstanceFields(this);

        this.admCopyView = admCopyView;
        this.context = context;
        copyService = context.getBean(CopyService.class);
        bookService =context.getBean(BookService.class);
        copyConverter =context.getBean(CopyConverter.class);

        statusDto.setItems(AVAILABLE, IN_USE, DESTROYED, LOST);
        bookId.setEnabled(false);

        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.setVisible(false);
        modify.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        modify.setVisible(false);

        id.setEnabled(false);


        save.addClickListener(click -> save());
        modify.addClickListener(click -> update());
        delete.addClickListener(click -> delete());

        VerticalLayout layout_Right =
                new VerticalLayout(
                        id,
                        bookId,
                        signature, statusDto, buttons);

        add(layout_Right);
    }


    public void setSaveAction() {
        setEnabled(true);
        setCopy(new ConvertedCopyDto());

        modify.setVisible(false);
        save.setVisible(true);
    }

    public void setUpdateAction() {
        setEnabled(true);
        save.setVisible(false);
        modify.setVisible(true);
    }

    public void setCopy(ConvertedCopyDto copy) {

        binder.setBean(copy);

    }


    private void save() {

        ConvertedCopyDto convertedCopyDto = binder.getBean();
        CopyDto copy = copyConverter.convertToCopyDto(convertedCopyDto);
        copyService.save(copy);
        admCopyView.refresh();
        setCopy(convertedCopyDto);
        setDisable();
    }

    private void update() {
        ConvertedCopyDto convertedCopyDto = binder.getBean();
        CopyDto copy = copyConverter.convertToCopyDto(convertedCopyDto);
        copyService.update(copy);
        admCopyView.refresh();
        setCopy(convertedCopyDto);
        setDisable();
    }

    private void delete() {
        ConvertedCopyDto convertedCopyDto = binder.getBean();
        CopyDto copy = copyConverter.convertToCopyDto(convertedCopyDto);
        long id = copy.getId();
        copyService.delete(id);
        setCopy(convertedCopyDto);
        admCopyView.refresh();
    }

    void setDisable() {
        setEnabled(false);
    }
}

