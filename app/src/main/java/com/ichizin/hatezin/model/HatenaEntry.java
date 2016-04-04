package com.ichizin.hatezin.model;



import android.content.Context;

import com.ichizin.hatezin.util.HatenaCategory;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

import timber.log.Timber;

/**
 *
 *
 * @author ichizin
 */
@Root(name = "item", strict = false)
public class HatenaEntry {

    private static final String IMAGE_PREFIX_URL = "http://cdn-ak.b.st-hatena.com/entryimage/";

    @Element(name = "title")
    private String title;

    @Element(name = "link")
    private String  link;

    @Element(name = "description", required = false)
    private String  description;

//    @Namespace(prefix = "dc")
//    @Element(name = "date")
//    private Date date;

    @Namespace(prefix = "content")
    @Element(name = "encoded")
    private String encoded;

    @Namespace(prefix = "dc")
    @ElementList(entry = "subject", inline = true, required = false)
    private List<String> subject;

    @Namespace(prefix = "hatena")
    @Element(name = "bookmarkcount")
    private int bookmarkCount;

    private boolean isTitle;

    private HatenaCategory hatenaCategory;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Date getDate() {
//        return date;
//    }
//
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
    public List<String> getSubject() {
        return subject;
    }

    public void setSubject(List<String> subject) {
        this.subject = subject;
    }

    public int getBookmarkCount() {
        return bookmarkCount;
    }

    public void setBookmarkCount(int bookmarkCount) {
        this.bookmarkCount = bookmarkCount;
    }

    public String getEncoded() {
        return encoded;
    }

    public void setEncoded(String encoded) {
        this.encoded = encoded;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public void setIsTitle(boolean isTitle) {
        this.isTitle = isTitle;
    }

    public HatenaCategory getHatenaCategory() {
        return hatenaCategory;
    }

    public void setHatenaCategory(HatenaCategory hatenaCategory) {
        this.hatenaCategory = hatenaCategory;
    }

    public String getCategoryName(Context context) {
        return hatenaCategory.getName(context);
    }

    public String getImageUrl() {

        Timber.d(encoded);
        Timber.d(String.valueOf(encoded.indexOf(IMAGE_PREFIX_URL)));
        Timber.d(String.valueOf(encoded.length()));

        int index = encoded.indexOf(IMAGE_PREFIX_URL);

        if(index > 0) {
            String url = encoded.substring(encoded.indexOf(IMAGE_PREFIX_URL),
                    encoded.length() - 1);
            return url.substring(0, url.indexOf("jpg") == 0? url.indexOf("jpeg") + 4 : url.indexOf("jpg") + 3);
        } else {
            return null;
        }
    }
}
