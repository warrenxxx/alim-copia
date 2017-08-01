package controlador.articulo;

import clases.CArticulo;
import clases.CCategoria;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;

/**
 *
 * @author WARREN
 */
public class ComboBoxEditingCell extends TableCell<modeloArticulo, modeloCategoria> {
        private ComboBox<modeloCategoria> comboBox;
        ArrayList<CCategoria> cat;
        CArticulo art;
        public ComboBoxEditingCell(ArrayList d ) {
            cat=d;
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createComboBox();
                setText(null);
                setGraphic(comboBox);
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText(getTyp().getNombre());
            setGraphic(null);
        }

        @Override
        public void updateItem(modeloCategoria item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (comboBox != null) {
                        comboBox.setValue(getTyp());
                    }
                    setText(getTyp().getNombre());
                    setGraphic(comboBox);
                } else {
                    setText(getTyp().getNombre());
                    setGraphic(null);
                }
            }
        }
    ObservableList<modeloCategoria> data= FXCollections.observableArrayList();;
        private void createComboBox(  ) {
            for(CCategoria x:cat){
                data.add(new modeloCategoria(x));
            }
            comboBox = new ComboBox<>(data);
            comboBoxConverter(comboBox);
            comboBox.valueProperty().set(getTyp());
            comboBox.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            comboBox.setOnAction((e) -> {
                commitEdit(comboBox.getSelectionModel().getSelectedItem());                
            });
//            comboBox.focusedProperty().addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
//                if (!newValue) {
//                    commitEdit(comboBox.getSelectionModel().getSelectedItem());
//                }
//            });
        }

        private void comboBoxConverter(ComboBox<modeloCategoria> comboBox) {
            // Define rendering of the list of values in ComboBox drop down. 
            comboBox.setCellFactory((c) -> {
                return new ListCell<modeloCategoria>() {
                    @Override
                    protected void updateItem(modeloCategoria item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getNombre());
                        }
                    }
                };
            });
        }

        private modeloCategoria getTyp() {
            return getItem() == null ? new modeloCategoria(new CCategoria()) : getItem();
        }
}
