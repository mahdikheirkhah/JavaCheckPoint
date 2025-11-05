public class BreakdownURL {
    public Map<String, String> parseURL(String url) {
        String[] parts = url.split("://");
        Stirng protocol = parts[0];
        String [] parts = parts[1].split("/");
        String domian = parts[0];
        String port;

        if( domian.contains(":") ) {
            domain = domain.split(":")[0];
            port = domain.split(":")[1];
        }
        String path ="/";
        String[] parts = parts[1].split("?");
        String query;
        if (parts.length == 2) {
            path += parts[0];
            query = parts[1];
        }
        result =
        return  new HashMap<String, String>() {{protocol},{domain},{port?},{path},{query?}}
    }
}