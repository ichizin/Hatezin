package com.ichizin.hatezin.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.Date;
import java.util.List;

/**
 * Created by ichizin on 16/03/24.
 *
 * @author ichizin
 */
@Root(name = "item", strict = false)
public class HatenaEntry {

    @Element(name = "title")
    private String title;

    @Element(name = "link")
    private String  link;

    @Element(name = "description", required = false)
    private String  description;

//    @Namespace(prefix = "dc")
//    @Element(name = "date")
//    private Date date;
//
//    @Namespace(prefix = "dc")
//    @ElementList(entry = "subject", inline = true, required = false)
//    private List<String> subject;
//
//    @Namespace(prefix = "hatena")
//    @Element(name = "bookmarkcount")
//    private int bookmarkCount;

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
//    public List<String> getSubject() {
//        return subject;
//    }
//
//    public void setSubject(List<String> subject) {
//        this.subject = subject;
//    }
//
//    public int getBookmarkCount() {
//        return bookmarkCount;
//    }
//
//    public void setBookmarkCount(int bookmarkCount) {
//        this.bookmarkCount = bookmarkCount;
//    }
}
