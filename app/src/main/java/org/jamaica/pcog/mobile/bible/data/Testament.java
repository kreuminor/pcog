package org.jamaica.pcog.mobile.bible.data;

import java.util.ArrayList;

import android.net.Uri;
import android.provider.BaseColumns;

import org.jamaica.pcog.mobile.bible.providers.BibleProvider;

public class Testament implements BaseColumns {
    public static final Uri CONTENT_URI = Uri.parse(BibleProvider.CONTENT_URI + "/testaments");

    public static final String DEFAULT_SORT_ORDER = "id ASC";

    public static final String ID = "id";
    public static final String NAME = "Testament";

    public Integer id = null;
    public String name = null;
    public ArrayList<Book> books = null;
    

    public Testament(Integer id, String name) {
	super();
	this.id = id;
	this.name = name;
    }
}
