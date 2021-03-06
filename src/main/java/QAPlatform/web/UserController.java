package QAPlatform.web;

import QAPlatform.model.User;
import QAPlatform.service.*;
import QAPlatform.validator.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Klasa będąca kontrolerem słuzącym do autoryzacji i rejestracji uzytkownika
 * @author Amadeusz Mileskzo
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    

    /**
     * @param model
     *      model uytkownika
	 * @return widok formularza słuzącego do dodawania rejestracji
	 */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    /**
     * Obsługa formularza rejestracji
     * @param userForm
     *      formularz uzytkownika
     * @param bindingResult
     *      rezulatat łączenia danych z modelem
     * @param model
     *      model uzytkownika
	 * @return widok formularza słuzącego do rejestracji wraz z błędami w przypadku niepowodzenia lub strona główna w przypadku pomyślnej operacji
	 */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());
        
        return "redirect:/";
    }

    /**
     * @param model
     *      model uzytkownika
     * @param error
     *      błędy
     * @param logout
     *      czy wylogowano
	 * @return widok formularza słuzącego do logowania i ewentualne błędy
	 */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    /**
     * czyszczenie uzytkownikow
     * @return przekierowanie do strony logowania
     */
    @Transactional
    @RequestMapping(value="/clean", method = RequestMethod.GET)
    public String clean()
    {
        userService.cleanUsers();

        return "redirect:/login";
    }
}