package org.jamaica.pcog.mobile.bible.data;

import java.util.ArrayList;

import android.net.Uri;
import android.provider.BaseColumns;

import org.jamaica.pcog.mobile.bible.providers.BibleProvider;

public class Book implements BaseColumns {
    public static final Uri CONTENT_URI = Uri.parse(BibleProvider.CONTENT_URI + "/books");

    public static final String DEFAULT_SORT_ORDER = "id";

    public static final String ID = "id";
    public static final String NAME = "Book";

    public Integer id = null;
    public String name = null;
    public ArrayList<Chapter> chapters = null;
    

    public Book(Integer id, String name) {
	super();
	this.id = id;
	this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }

    public static Uri getContentUri(final int bookId) {
	return Uri.parse(BibleProvider.CONTENT_URI + "/books/" + bookId);
    }
    
    public static String getWhereClause(final int bookId) {
	return "id = " + bookId;
    }
}
