
package com.ant_robot.mfc.api.pojo;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class UserCollectionOwned {

    @Expose
    private String name;
    @Expose
    private String version;
    @Expose
    private Collection collection;

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public UserCollectionOwned withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The version
     */
    public String getVersion() {
        return version;
    }

    /**
     * 
     * @param version
     *     The version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    public UserCollectionOwned withVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * 
     * @return
     *     The collection
     */
    public Collection getCollection() {
        return collection;
    }

    /**
     * 
     * @param collection
     *     The collection
     */
    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public UserCollectionOwned withCollection(Collection collection) {
        this.collection = collection;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(version).append(collection).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UserCollectionOwned) == false) {
            return false;
        }
        UserCollectionOwned rhs = ((UserCollectionOwned) other);
        return new EqualsBuilder().append(name, rhs.name).append(version, rhs.version).append(collection, rhs.collection).isEquals();
    }

}
