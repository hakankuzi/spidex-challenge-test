package demo.getting_started.mvc;

import java.util.List;
import java.util.Set;

import demo.getting_started.repository.MockDataService;
import demo.getting_started.repository.MockDataServiceImpl;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;
import org.zkoss.zul.ext.Selectable;

import demo.getting_started.tutorial.Car;
import demo.getting_started.tutorial.CarService;
import demo.getting_started.tutorial.CarServiceImpl;

public class SearchController extends SelectorComposer<Component> {

    private static final long serialVersionUID = 1L;

    @Wire
    private Textbox keywordBox;
    @Wire
    private Listbox carListBox;
    @Wire
    private Label modelLabel;
    @Wire
    private Label colourLabel;
    @Wire
    private Label makeLabel;
    @Wire
    private Label priceLabel;
    @Wire
    private Label descriptionLabel;
    @Wire
    private Image previewImage;
    @Wire
    private Component detailBox;

    @Wire
    private Button removeBtn;

    private CarService carService = new CarServiceImpl();
    private MockDataService mockDataService = new MockDataServiceImpl();


    @Override
    public void doAfterCompose(Component comp) throws Exception {
        // TODO Auto-generated method stub
        super.doAfterCompose(comp);
        refreshListBox();
        removeBtn.setDisabled(true);
    }

    @Listen("onClick = #gotoAddBtn")
    public void saveButton() {
        Executions.sendRedirect("/save.zul");
    }

    @Listen("onClick = #searchButton")
    public void search() {
        String keyword = keywordBox.getValue();
        List<Car> result = carService.search(keyword);
        if (result.size() == 0)
            carListBox.setVisible(false);
        else
            carListBox.setVisible(true);
        carListBox.setModel(new ListModelList<Car>(result));
    }

    @Listen("onSelect = #carListBox")
    public void showDetail() {
        removeBtn.setDisabled(false);
        detailBox.setVisible(true);
        Car car = selectedCar();
        Set<Car> selection = ((Selectable<Car>) carListBox.getModel()).getSelection();
        if (selection != null && !selection.isEmpty()) {
            previewImage.setSrc(car.getPreview());
            modelLabel.setValue(car.getModel());
            makeLabel.setValue(car.getMake());
            priceLabel.setValue(car.getPrice().toString());
            descriptionLabel.setValue(car.getDescription());
            colourLabel.setValue(car.getColour());
        }
    }

    @Listen("onClick = #removeBtn")
    public void removeClick() {
        Messagebox.show(
                "Are you sure to delete ?", "Confirm Dialog",
                Messagebox.OK | Messagebox.CANCEL,
                Messagebox.QUESTION,
                new org.zkoss.zk.ui.event.EventListener() {
                    @Override
                    public void onEvent(Event evt) {
                        if (evt.getName().equals("onOK")) {
                            Car car = selectedCar();
                            mockDataService.removeCar(car);
                            refreshListBox();
                        } else {

                            // Just in case
                            System.out.println("Delete Canceled !");
                        }
                    }
                }
        );
    }

    private Car selectedCar() {
        Set<Car> selection = ((Selectable<Car>) carListBox.getModel()).getSelection();
        if (selection != null && !selection.isEmpty()) {
            return selection.iterator().next();
        } else {
            return null;
        }
    }

    private void refreshListBox() {
        List<Car> list = carService.findAll();
        carListBox.setModel(new ListModelList<Car>(list));
    }
}
