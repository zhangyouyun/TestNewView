package android.com.testnewview;

import java.util.Arrays;

public class Item {
    private String name;
    private boolean check;
    private CustomTextView customTextView;
    private int id;
    private String[] Child;

    public Item(String name, boolean check, CustomTextView tv, int id) {
        this.check = check;
        this.id = id;
        this.name = name;
        this.customTextView = tv;
    }

    public String[] getChild() {
        return Child;
    }

    public void setChild(String[] child) {
        Child = child;
    }

    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", check=" + check +
                ", customTextView=" + customTextView +
                ", id=" + id +
                ", Child=" + Arrays.toString(Child) +
                '}';
    }

    public Item(String name, boolean check, CustomTextView customTextView, int id, String[] child) {
        this.name = name;
        this.check = check;
        this.customTextView = customTextView;
        this.id = id;
        Child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public CustomTextView getTv() {
        return customTextView;
    }

    public void setTv(CustomTextView tv) {
        this.customTextView = tv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
