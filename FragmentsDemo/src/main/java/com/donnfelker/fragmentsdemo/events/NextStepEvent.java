package com.donnfelker.fragmentsdemo.events;

/**
 * Pub/Sub event for going to the next step.
 */
public class NextStepEvent {

    private CharSequence firstNameText;
    private CharSequence lastNameText;
    private CharSequence address;

    public NextStepEvent() {}

    public NextStepEvent(CharSequence firstNameText, CharSequence lastNameText) {
        this.firstNameText = firstNameText;
        this.lastNameText = lastNameText;
    }

    public NextStepEvent(CharSequence address) {

        this.address = address;
    }

    public CharSequence getFirstNameText() {
        return firstNameText;
    }

    public void setFirstNameText(CharSequence firstNameText) {
        this.firstNameText = firstNameText;
    }

    public CharSequence getLastNameText() {
        return lastNameText;
    }

    public void setLastNameText(CharSequence lastNameText) {
        this.lastNameText = lastNameText;
    }

    public CharSequence getAddress() {
        return address;
    }

    public void setAddress(CharSequence address) {
        this.address = address;
    }
}
