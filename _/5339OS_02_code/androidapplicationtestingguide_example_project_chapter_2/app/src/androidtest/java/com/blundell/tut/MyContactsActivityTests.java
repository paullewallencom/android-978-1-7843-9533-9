package com.blundell.tut;

import android.net.Uri;
import android.provider.ContactsContract;
import android.test.AndroidTestCase;

public class MyContactsActivityTests extends AndroidTestCase {
    private final static String PKG = "com.blundell.tut";
    private final static String ACTIVITY = PKG + ".MyContactsActivity";
    private final static String PERMISSION = android.Manifest.permission.CALL_PHONE;

    public MyContactsActivityTests() {
        this("MyContactsActivityTests");
    }

    public MyContactsActivityTests(String name) {
        setName(name);
    }

    public void testActivityPermission() {
        assertActivityRequiresPermission(PKG, ACTIVITY, PERMISSION);
    }

//    public void testActivityPermissionMyWay() {
//        Intent intent = new Intent();
//        intent.setClassName(PKG, ACTIVITY);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        getContext().startActivity(intent);
//    }

    public void testReadingContacts() {
        Uri URI = ContactsContract.AUTHORITY_URI;
        String PERMISSION = android.Manifest.permission.READ_CONTACTS;
        assertReadingContentUriRequiresPermission(URI, PERMISSION);
    }

    public void testWritingContacts() {
        Uri URI = ContactsContract.AUTHORITY_URI;
        String PERMISSION = android.Manifest.permission.WRITE_CONTACTS;
        assertWritingContentUriRequiresPermission(URI, PERMISSION);
    }
}
