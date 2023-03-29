package decorators.interface_segregation;

import decorators.Element;
import decorators.find_strategy.FindStrategy;

import java.util.List;

public interface ElementFindService {
    Element findById(String id);
    Element findByXPath(String xpath);
    Element findByTag(String tag);
    Element findByClass(String cssClass);
    Element findByCss(String css);
    Element findByLinkText(String linkText);
    List<Element> findAllById(String id);
    List<Element> findAllByXPath(String xpath);
    List<Element> findAllByTag(String tag);
    List<Element> findAllByClass(String cssClass);
    List<Element> findAllByCss(String css);
    List<Element> findAllByLinkText(String linkText);
    Element findByName(String name);
    List<Element> findAll(FindStrategy findStrategy);
    Element find(FindStrategy findStrategy);

}
