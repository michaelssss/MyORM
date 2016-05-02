/**
 * 作者： 梁煜铭<p>
 * 创建时间： 16-5-2<p>
 * 类作用：PACKAGE_NAME<p>
 */
public class MyObject {
    private String id;
    private String Content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyObject)) return false;

        MyObject myObject = (MyObject) o;

        if (getId() != null ? !getId().equals(myObject.getId()) : myObject.getId() != null) return false;
        return getContent() != null ? getContent().equals(myObject.getContent()) : myObject.getContent() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("this Object Content such Mesaage:");
        stringBuffer.append("Object=[id : " + getId());
        stringBuffer.append("Content : " + getContent());
        stringBuffer.append(" ]");
        return stringBuffer.toString().trim();
    }
}
