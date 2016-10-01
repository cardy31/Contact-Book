package org.cardy.spring.controller;

import org.cardy.spring.dao.RefDAO;
import org.cardy.spring.model.Ref;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private RefDAO refDAO;

    @RequestMapping(value = "/")
    public List<Ref> listAccounts(ModelAndView model) throws IOException {
        List<Ref> listRef = refDAO.list();
        model.addObject("listContact", listRef);
        model.setViewName("home");

        return listRef;
    }

    @RequestMapping(value = "/newContact", method = RequestMethod.GET)
    public ModelAndView newContact(ModelAndView model) {
        Ref newRef = new Ref();
        model.addObject("contact", newRef);
        model.setViewName("ContactForm");
        return model;
    }

    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public ModelAndView saveContact(@ModelAttribute Ref ref) {
        refDAO.saveOrUpdate(ref);
        return new ModelAndView("redirect:/");
    }

    //TODO:
    @RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
    public ModelAndView deleteContact(HttpServletRequest request) {
        int contactId = Integer.parseInt(request.getParameter("id"));
        refDAO.delete(contactId);
        return new ModelAndView("redirect:/");
    }

    // TODO: Change this to accept data, instead of pushing a form
    @RequestMapping(value = "/editContact", method = RequestMethod.GET)
    public ModelAndView editContact(HttpServletRequest request) {
        int refId = Integer.parseInt(request.getParameter("id"));
        Ref ref = refDAO.get(refId);
        ModelAndView model = new ModelAndView("ContactForm");
        model.addObject("contact", ref);

        return model;
    }
}
