package com.ichizin.hatezin.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 *
 * @author ichizin
 */
@Namespace(prefix = "rdf")
@Root(name = "RDF", strict = false)
public class HatenaFeed {

    @ElementList(name = "item", inline = true)
    public List<HatenaEntry> items;
}
