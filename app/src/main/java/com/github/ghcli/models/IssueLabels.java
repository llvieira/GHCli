package com.github.ghcli.models;

import android.os.Parcel;
import android.os.Parcelable;

public class IssueLabels implements Parcelable {

    private String name;
    private String color;

    public IssueLabels() {
    }

    public IssueLabels(String name, String color) {
        this.name = name;
        this.color = color;
    }

    protected IssueLabels(Parcel in) {
        name = in.readString();
        color = in.readString();
    }

    public static final Parcelable.Creator<IssueLabels> CREATOR = new Parcelable.Creator<IssueLabels>() {
        @Override
        public IssueLabels createFromParcel(Parcel in) {
            return new IssueLabels(in);
        }

        @Override
        public IssueLabels[] newArray(int size) {
            return new IssueLabels[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "IssueLabels{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssueLabels that = (IssueLabels) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return color != null ? color.equals(that.color) : that.color == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
