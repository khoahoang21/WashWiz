package group5.webapp.FinalProjectOOP.controllers;

import group5.webapp.FinalProjectOOP.models.*;
import group5.webapp.FinalProjectOOP.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin")
public class AdminCRUDController {

    @Autowired
    UserService userService;

    @Autowired
    CustomerInfoService customerInfoService;
    @Autowired
    AddressService addressService;
    @Autowired
    CardService cardService;

    @Autowired
    UploadFileService uploadFileService;

    @Autowired
    ProductService productService;

    @Autowired
    CategoryService Cservice;

    @Autowired
    BillService billService;

    @Autowired
    BillDetailService billDetailService;

    @Autowired
    ProductImageService productImageService;

    @Autowired
    ProductDetailService productDetailService;


    private static final int PAGE_SIZE = 5;
    public static final int PAGE_SITE_B = 10;

    @RequestMapping(value = "/list-user/{page}")
    public String ListUserPage(@PathVariable Integer page,
                               HttpServletRequest rq,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                Page<User> userList = userService.pagingUser(page - 1, PAGE_SIZE);
                int amount = userService.findAll().size();

                int endPage = amount / PAGE_SIZE;

                if (amount % PAGE_SIZE != 0) {
                    endPage += 1;
                }

                if (page == null) {
                    page = 1;
                }

                model.addAttribute("userList", userList);
                model.addAttribute("tag", page);
                model.addAttribute("endPage", endPage);

                return "admin/list-user";
            }
        }
    }

    @RequestMapping(value = "/edit-user")
    public String saveUser(@RequestParam(required = false, name = "editID") Integer id,
                           @RequestParam(required = false, name = "editUserName") String username,
                           @RequestParam(required = false, name = "editPW") String password,
                           @RequestParam(required = false, name = "editStt") Integer status,
                           HttpServletRequest rq,
                           RedirectAttributes redirectAttributes) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                User newUser = userService.getUserById(id);
                newUser.setUserName(username);
                newUser.setStatus(status);
                newUser.setPassWord(password);
                userService.saveUser(newUser);
                redirectAttributes.addFlashAttribute("message", "Đã sửa thành công!!!");
                return "redirect:/admin/list-user/1";
            }
        }
    }

    @RequestMapping(value = "/delete-user")
    public String deleteUser(@RequestParam(required = false, name = "deleteID") Integer id,
                             @RequestParam(required = false, name = "resultDelete") String listID,
                             HttpServletRequest rq,
                             RedirectAttributes redirectAttributes) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                if (id != 0) {
                    userService.deleteUserById(id);
                } else {
                    String[] splitListId = listID.split(",");

                    for (String x : splitListId) {
                        userService.deleteUserById(Integer.parseInt(x));
                    }
                }
                redirectAttributes.addFlashAttribute("message", "Đã xoá thành công!!!");
                return "redirect:/admin/list-user/1";
            }
        }
    }

    @RequestMapping(value = "/add-user")
    public String insertUser(@RequestParam(required = false, name = "addUserName") String username,
                             @RequestParam(required = false, name = "addPW") String password,
                             @RequestParam(required = false, name = "addStt") Integer status,
                             HttpServletRequest rq,
                             RedirectAttributes redirectAttributes) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                User newUser = userService.getUserByUserName(username);
                if (newUser == null) {
                    newUser = new User();
                    newUser.setUserName(username);
                    newUser.setPassWord(password);
                    newUser.setRole(1);
                    newUser.setStatus(status);
                    userService.saveUser(newUser);
                    redirectAttributes.addFlashAttribute("message", "Đã thêm thành công!!!");
                } else {
                    redirectAttributes.addFlashAttribute("messageError", "Đã tồn tại tên tài khoản!!!");

                }
                return "redirect:/admin/list-user/1";
            }
        }
    }

    //----------------
    @RequestMapping(value = "/list-customerinfo/{page}")
    public String ListCustomerInfoPage(@PathVariable Integer page, HttpServletRequest rq,
                                       RedirectAttributes redirectAttributes,
                                       Model model) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                Page<CustomerInfo> customerInfoList = customerInfoService.pagingCustomerInfo(page - 1, PAGE_SIZE);

                int amount = customerInfoService.findAll().size();

                int endPage = amount / PAGE_SIZE;

                if (amount % PAGE_SIZE != 0) {
                    endPage += 1;
                }

                if (page == null) {
                    page = 1;
                }

                List<User> userList = userService.findAllByRoleAndStatus(1, 1);
                List<CustomerInfo> customerInfoListTemp = customerInfoService.findAll();
                boolean flag = true;
                for (int i = 0; i < userList.size(); ) {
                    for (int j = 0; j < customerInfoListTemp.size(); j++) {
                        if (userList.get(i).getId() == customerInfoListTemp.get(j).getUser().getId()) {
                            userList.remove(i);
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        i++;
                    } else {
                        flag = true;
                    }
                }

                model.addAttribute("tag", page);
                model.addAttribute("endPage", endPage);
                model.addAttribute("customerInfoList", customerInfoList);
                model.addAttribute("listUser", userList);
                return "admin/list-customerinfo";
            }
        }
    }

    @RequestMapping(value = "/add-customerinfo")
    public String AddCustomerInfo(@RequestParam(required = false, name = "addUserID") Integer addUserID,
                                  @RequestParam(required = false, name = "addFullName") String addFullName,
                                  @RequestParam(required = false, name = "addBirthday") Date addBirthday,
                                  @RequestParam(required = false, name = "addEmail") String addEmail,
                                  @RequestParam(required = false, name = "addPhone") String addPhone,
                                  @RequestParam(required = false, name = "addAVT") MultipartFile addAVT,
                                  HttpServletRequest rq,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                CustomerInfo customerInfo = new CustomerInfo();
                customerInfo.setUser(userService.getUserById(addUserID));
                customerInfo.setFullname(addFullName);
                customerInfo.setBithday(addBirthday);
                customerInfo.setPhone(addPhone);
                customerInfo.setEmail(addEmail);

                if (addAVT != null) {
                    customerInfo.setLinkAVT(uploadFileService.storeFile(addAVT));
                }

                customerInfoService.saveInfo(customerInfo);
                redirectAttributes.addFlashAttribute("message", "Đã thêm thành công!!!");
                return "redirect:/admin/list-customerinfo/1";
            }
        }
    }

    @RequestMapping(value = "/edit-customerinfo")
    public String EditCustomerInfo(@RequestParam(required = false, name = "editID") Integer editID,
                                   @RequestParam(required = false, name = "editFullName") String editFullName,
                                   @RequestParam(required = false, name = "editBirthday") Date editBirthday,
                                   @RequestParam(required = false, name = "editEmail") String editEmail,
                                   @RequestParam(required = false, name = "editPhone") String editPhone,
                                   @RequestParam(required = false, name = "editAVT") MultipartFile editAVT,
                                   HttpServletRequest rq,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                System.out.println(editID);
                CustomerInfo customerInfo = customerInfoService.getByID(editID);
                customerInfo.setFullname(editFullName);
                customerInfo.setBithday(editBirthday);
                customerInfo.setPhone(editPhone);
                customerInfo.setEmail(editEmail);

                if (editAVT != null) {
                    customerInfo.setLinkAVT(uploadFileService.storeFile(editAVT));
                }

                customerInfoService.saveInfo(customerInfo);
                redirectAttributes.addFlashAttribute("message", "Đã sửa thành công!!!");
                return "redirect:/admin/list-customerinfo/1";
            }
        }
    }

    @RequestMapping(value = "/delete-customerinfo")
    public String deleteCustomerInfo(@RequestParam(required = false, name = "deleteCustomerInfo") Integer deleteID,
                                     @RequestParam(required = false, name = "resultCustomerInfo") String listID,
                                     HttpServletRequest rq,
                                     RedirectAttributes redirectAttributes) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                if (deleteID != 0) {
                    customerInfoService.deletInfoById(deleteID);
                } else {
                    String[] listIDInt = listID.split(",");
                    for (String idTemp : listIDInt) {
                        customerInfoService.deletInfoById(Integer.parseInt(idTemp));
                    }
                }
                redirectAttributes.addFlashAttribute("message", "Đã xoá thành công!!!");
                return "redirect:/admin/list-customerinfo/1";
            }
        }
    }


    @RequestMapping(value = "/list-card/{page}")
    public String ListCardPage(@PathVariable Integer page,
                               HttpServletRequest rq,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                Page<Card> cardList = cardService.pagingCard(page - 1, PAGE_SIZE);

                int amount = cardService.findAll().size();

                int endPage = amount / PAGE_SIZE;

                if (amount % PAGE_SIZE != 0) {
                    endPage += 1;
                }

                if (page == null) {
                    page = 1;
                }

                List<User> userList = userService.findAll();

                model.addAttribute("cardList", cardList);
                model.addAttribute("tag", page);
                model.addAttribute("endPage", endPage);
                model.addAttribute("listUser", userList);
                return "admin/list-card";
            }
        }
    }


    @RequestMapping(value = "/add-card")
    public String AddCard(@RequestParam(required = false, name = "addUserID") Integer addUserID,
                          @RequestParam(required = false, name = "addBank") String addBank,
                          @RequestParam(required = false, name = "addNumber") String addNumber,
                          HttpServletRequest rq,
                          RedirectAttributes redirectAttributes,
                          Model model) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                Card card = new Card();
                card.setUser(userService.getUserById(addUserID));
                card.setBank(addBank);
                card.setNumber(addNumber);
                cardService.saveCard(card);
                redirectAttributes.addFlashAttribute("message", "Đã thêm thành công!!!");
                return "redirect:list-card/1";
            }
        }
    }


    @RequestMapping(value = "/edit-card")
    public String EditCard(@RequestParam(required = false, name = "editID") Integer editID,
                           @RequestParam(required = false, name = "editBank") String editBank,
                           @RequestParam(required = false, name = "editNumber") String editNumber,
                           HttpServletRequest rq,
                           RedirectAttributes redirectAttributes,
                           Model model) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                Card card = cardService.getById(editID);
                card.setBank(editBank);
                card.setNumber(editNumber);
                cardService.saveCard(card);
                redirectAttributes.addFlashAttribute("message", "Đã sửa thành công!!!");
                return "redirect:list-card/1";
            }
        }
    }


    @RequestMapping(value = "/delete-card")
    public String DeleteCard(@RequestParam(required = false, name = "deleteCard") Integer deleteCard,
                             @RequestParam(required = false, name = "resultCard") String resultCard,
                             HttpServletRequest rq,
                             RedirectAttributes redirectAttributes,
                             Model model) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                if (deleteCard != 0) {
                    cardService.deleteCardById(deleteCard);
                } else {
                    String[] listID = resultCard.split(",");
                    for (String idTemp : listID) {
                        cardService.deleteCardById(Integer.parseInt(idTemp));
                    }
                }
                redirectAttributes.addFlashAttribute("message", "Đã xoá thành công!!!");
                return "redirect:list-card/1";
            }
        }
    }


    @RequestMapping(value = "/list-address/{page}")
    public String ListAddressPage(@PathVariable Integer page,
                                  HttpServletRequest rq,
                                  RedirectAttributes redirectAttributes,
                                  Model model) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                Page<Address> addressList = addressService.pagingAddress(page - 1, PAGE_SIZE);
                int amount = addressService.findAll().size();

                int endPage = amount / PAGE_SIZE;

                if (amount % PAGE_SIZE != 0) {
                    endPage += 1;
                }

                if (page == null) {
                    page = 1;
                }

                List<User> userList = userService.findAll();
                model.addAttribute("addressList", addressList);
                model.addAttribute("tag", page);
                model.addAttribute("endPage", endPage);
                model.addAttribute("listUser", userList);
                return "admin/list-address";
            }
        }
    }


    @RequestMapping(value = "/add-address")
    public String AddAddress(@RequestParam(required = false, name = "addUserID") Integer addUserID,
                             @RequestParam(required = false, name = "addDescription") String addDescription,
                             HttpServletRequest rq,
                             RedirectAttributes redirectAttributes,
                             Model model) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                Address address = new Address();
                address.setUser(userService.getUserById(addUserID));
                address.setDescription(addDescription);
                addressService.saveAddress(address);
                redirectAttributes.addFlashAttribute("message", "Đã thêm thành công!!!");
                return "redirect:list-address/1";
            }
        }
    }


    @RequestMapping(value = "/edit-address")
    public String EditAddress(@RequestParam(required = false, name = "editID") Integer editID,
                              @RequestParam(required = false, name = "editDescription") String editDescription,
                              HttpServletRequest rq,
                              RedirectAttributes redirectAttributes,
                              Model model) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                Address address = addressService.getById(editID);
                address.setDescription(editDescription);
                addressService.saveAddress(address);
                redirectAttributes.addFlashAttribute("message", "Đã sửa thành công!!!");
                return "redirect:list-address/1";
            }
        }
    }


    @RequestMapping(value = "/delete-address")
    public String DeleteAddress(@RequestParam(required = false, name = "deleteAddress") Integer deleteAddress,
                                @RequestParam(required = false, name = "resultAddress") String resultAddress,
                                HttpServletRequest rq,
                                RedirectAttributes redirectAttributes,
                                Model model) {

        HttpSession session = rq.getSession();
        User user = (User) session.getAttribute("account");
        if (user == null) {
            redirectAttributes.addFlashAttribute("message", "Vui lòng đăng nhập!!!");
            return "redirect:/admin/login";
        } else {
            if (user.getRole() != 3) {
                redirectAttributes.addFlashAttribute("message", "Tài khoản không có quyền admin!!!");
                return "redirect:/admin/login";
            } else {
                //do some thing
                if (deleteAddress != 0) {
                    addressService.deleteAddressById(deleteAddress);
                } else {
                    String[] listID = resultAddress.split(",");
                    for (String idTemp : listID) {
                        addressService.deleteAddressById(Integer.parseInt(idTemp));
                    }
                }
                redirectAttributes.addFlashAttribute("message", "Đã xoá thành công!!!");
                return "redirect:list-address/1";
            }
        }
    }

    @RequestMapping(value = {"/list-product/{pagenumber}"})
    public String ListAllProduct(HttpServletRequest rq,
                                 @PathVariable("pagenumber") Integer pagenumber,
                                 Model model){
        HttpSession session = rq.getSession();
        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                String indexPage = String.valueOf(pagenumber);
                if (indexPage == null) {
                    indexPage = "1";
                }

                List<Product> listAllProduct = productService.findAll();

                int count = listAllProduct.size();
                int index = Integer.parseInt(indexPage);

                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage = endPage + 1;
                }

                List<Category> listAllCategory = Cservice.findAll();

                List<Product> listProductPaging = productService.PagingAllProduct(pagenumber -1 , PAGE_SITE_B).getContent();
                model.addAttribute("endP", endPage);
                model.addAttribute("tag", index);
                model.addAttribute("listAllProduct", listProductPaging);
                model.addAttribute("listAllCategory", listAllCategory);
                model.addAttribute("counts", listAllProduct.size());
                //model.addAttribute("check", check);
                return "admin/list-product";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value = {"/edit-product"}, method = RequestMethod.POST)
    public String EditProduct(HttpServletRequest rq,
                              @RequestParam(required = false, value = "editID") int id,
                              @RequestParam(required = false, value = "editName") String name,
                              @RequestParam(required = false, value = "editPrice") int price,
                              @RequestParam(required = false, value = "editAmount") int amount,
                              @RequestParam(required = false, value = "editQuantity") int quantity,
                              @RequestParam(required = false, value = "editCategory") int categoryID,
                              @RequestParam("thumbnailPhoto") MultipartFile thumbnailPhoto,
                              Model model, RedirectAttributes redirectAttributes){
        HttpSession session = rq.getSession();
        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    Product product = productService.getProductById(id);
                    product.setName(name);
                    product.setPrice(price);
                    product.setAmount(amount);
                    product.setQuantity(quantity);
                    product.setCategory(Cservice.findCategoryById(categoryID));

                    try {
                        product.setThumbnailPhoto(uploadFileService.storeFile(thumbnailPhoto));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    productService.saveProduct(product);
                    String message = "Edit Product Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Edit Product Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }

                return "redirect:/admin/list-product/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }


    }

    @RequestMapping(value = {"/add-product"}, method = RequestMethod.POST)
    public String AddProduct(HttpServletRequest rq,
                             @RequestParam(required = false, value = "addName") String name,
                             @RequestParam(required = false, value = "addPrice") int price,
                             @RequestParam(required = false, value = "addQuantity") int quantity,
                             @RequestParam(required = false, value = "addCategory") int category,
                             @RequestParam("addThumbnailPhoto") MultipartFile thumbnailPhoto,
                             Model model, RedirectAttributes redirectAttributes){


        HttpSession session = rq.getSession();
        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    Product product = new Product();
                    product.setPrice(price);
                    product.setAmount(0);
                    product.setQuantity(quantity);
                    product.setName(name);
                    product.setCategory(Cservice.findCategoryById(category));

                    try {
                        product.setThumbnailPhoto(uploadFileService.storeFile(thumbnailPhoto));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    productService.saveProduct(product);
                    String message = "Add Product Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Add Product Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }

                return "redirect:/admin/list-product/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value ="/delete-product", method = RequestMethod.POST)
    public String deleteProduct(HttpServletRequest rq,
                                @RequestParam(required = false, value = "deleteProduct") int id,
                                @RequestParam(required = false, value = "result") String result,
                                RedirectAttributes redirectAttributes){
        HttpSession session = rq.getSession();
        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    if (id != 0){
                        productService.deleteProduct(id);
                    }else {
                        String[] listID = result.split(",");
                        for (String idP : listID){
                            productService.deleteProduct(Integer.parseInt(idP));
                        }
                    }
                    String message = "Delete Product Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Delete Product Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }


                return "redirect:/admin/list-product/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }

    }

    @RequestMapping(value = {"/list-category/{pagenumber}"})
    public String ListAllCategory(HttpServletRequest rq,
                                  @PathVariable("pagenumber") Integer pagenumber,
                                  Model model, RedirectAttributes redirectAttributes){

        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                String indexPage = String.valueOf(pagenumber);
                if (indexPage == null) {
                    indexPage = "1";
                }

                List<Category> listAllCategory = Cservice.findAll();

                int count = listAllCategory.size();
                int index = Integer.parseInt(indexPage);

                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage = endPage + 1;
                }

                //List<Category> listAllCategory = Cservice.findAll();

                List<Category> listCategoryPaging = Cservice.PagingAllCategory(pagenumber -1 , PAGE_SITE_B).getContent();
                model.addAttribute("endP", endPage);
                model.addAttribute("tag", index);
                model.addAttribute("listAllCategory", listCategoryPaging);
                model.addAttribute("counts", count);
                //model.addAttribute("check", check);
                return "admin/list-category";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value = {"/add-category"}, method = RequestMethod.POST)
    public String AddProduct(HttpServletRequest rq,
                             @RequestParam(required = false, value = "addName") String name,
                             Model model, RedirectAttributes redirectAttributes){

        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    Category category = new Category();
                    category.setName(name);
                    Cservice.saveCategory(category);
                    String message = "Add Category Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Add Category Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }

                return "redirect:/admin/list-category/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value = {"/edit-category"}, method = RequestMethod.POST)
    public String EditCategory(HttpServletRequest rq,
                               @RequestParam(required = false, value = "editID") int id,
                               @RequestParam(required = false, value = "editName") String name,
                               Model model, RedirectAttributes redirectAttributes){
        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    Category category = Cservice.findCategoryById(id);
                    category.setName(name);

                    Cservice.saveCategory(category);
                    String message = "Edit Category Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Edit Category Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }

                return "redirect:/admin/list-category/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value ="/delete-category", method = RequestMethod.POST)
    public String deleteCategory(HttpServletRequest rq,
                                 @RequestParam(required = false, value = "deleteCategory") int id,
                                 @RequestParam(required = false, value = "result") String result,
                                 RedirectAttributes redirectAttributes){
        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    if (id != 0){
                        Cservice.deleteCategory(id);
                    }else {
                        String[] listID = result.split(",");
                        for (String idC : listID){
                            Cservice.deleteCategory(Integer.parseInt(idC));
                        }
                    }

                    String message = "Delete Category Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Delete Category Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }

                return "redirect:/admin/list-category/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }

    }

    @RequestMapping(value = {"/list-productimage/{pagenumber}"})
    public String ListAllProductImg(HttpServletRequest rq,
                                    @PathVariable("pagenumber") Integer pagenumber,
                                    Model model){

        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                String indexPage = String.valueOf(pagenumber);
                if (indexPage == null) {
                    indexPage = "1";
                }

                List<ProductImage> listAllProductImg = productImageService.findAll();

                int count = listAllProductImg.size();
                int index = Integer.parseInt(indexPage);

                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage = endPage + 1;
                }

                List<Product> listAllProduct = productService.findAll();

                List<ProductImage> productImageList = productImageService.PagingAllProductImg(pagenumber -1 , PAGE_SITE_B).getContent();
                model.addAttribute("endP", endPage);
                model.addAttribute("tag", index);
                model.addAttribute("listAllProductImg", productImageList);
                model.addAttribute("listAllProduct", listAllProduct);
                model.addAttribute("counts", count);
                //model.addAttribute("check", check);
                return "admin/list-productimage";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value = {"/add-productimage"}, method = RequestMethod.POST)
    public String AddProductImg(HttpServletRequest rq,
                                @RequestParam("addLink") MultipartFile thumbnailPhoto,
                                @RequestParam(required = false, value = "addProduct") int idProduct,
                                Model model, RedirectAttributes redirectAttributes){
        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    ProductImage productImage = new ProductImage();
                    try {
                        productImage.setLink(uploadFileService.storeFile(thumbnailPhoto));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    productImage.setProduct(productService.getProductById(idProduct));
                    productImageService.save(productImage);
                    String message = "Add Product Imagge Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Add Product Imagge Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }

                return "redirect:/admin/list-productimage/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value = {"/edit-productimage"}, method = RequestMethod.POST)
    public String EditProductImg(HttpServletRequest rq,
                                 @RequestParam(required = false, value = "editID") int id,
                                 @RequestParam("editLink") MultipartFile thumbnailPhoto,
                                 Model model, RedirectAttributes redirectAttributes){
        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    ProductImage productImage = productImageService.getProductImgById(id);

                    try {
                        productImage.setLink(uploadFileService.storeFile(thumbnailPhoto));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    productImageService.save(productImage);
                    String message = "Edit Product Imagge Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Edit Product Imagge Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }

                return "redirect:/admin/list-productimage/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value ="/delete-productimage", method = RequestMethod.POST)
    public String deleteProductImg(HttpServletRequest rq,
                                   @RequestParam(required = false, value = "deleteProductImg") int id,
                                   @RequestParam(required = false, value = "result") String result,
                                   RedirectAttributes redirectAttributes){
        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    if (id != 0){
                        productImageService.deleteProductImg(id);
                    }else {
                        String[] listID = result.split(",");
                        for (String idC : listID){
                            Cservice.deleteCategory(Integer.parseInt(idC));
                        }
                    }

                    String message = "Delete Product Imagge Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Delete Product Imagge Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }

                return "redirect:/admin/list-productimage/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }

    }

    @RequestMapping(value = {"/list-productdetail/{pagenumber}"})
    public String ListAllProductDetail(HttpServletRequest rq,
                                       @PathVariable("pagenumber") Integer pagenumber,
                                       Model model){

        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                String indexPage = String.valueOf(pagenumber);
                if (indexPage == null) {
                    indexPage = "1";
                }

                List<ProductDetail> listAllProductDetail = productDetailService.findAll();
                //System.out.println("name" + listAllCategory.get(0).getName());

                int count = listAllProductDetail.size();
                int index = Integer.parseInt(indexPage);

                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage = endPage + 1;
                }

                List<Product> listAllProduct = productService.findAll();
                List<Product> listProductNoDetail = new ArrayList<>();
                for (Product p : listAllProduct){
                    int i = 0;
                    for (ProductDetail pd : listAllProductDetail){
                        if (p.getId() == pd.getProduct().getId()){
                            break;
                        }
                        i++;
                    }
                    if (i == listAllProductDetail.size()){
                        listProductNoDetail.add(p);
                    }
                }

                List<ProductDetail> listProductDetailPaging = productDetailService.PagingAllProductDetail(pagenumber -1 , PAGE_SITE_B).getContent();
                model.addAttribute("endP", endPage);
                model.addAttribute("tag", index);
                model.addAttribute("listAllProductDetail", listProductDetailPaging);
                model.addAttribute("listAllProduct", listProductNoDetail);
                model.addAttribute("counts", count);
                //model.addAttribute("check", check);
                return "admin/list-productdetail";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value = {"/add-productdetail"}, method = RequestMethod.POST)
    public String AddProductDetail(HttpServletRequest rq,
                                   @RequestParam(required = false, value = "addBrand") String brand,
                                   @RequestParam(required = false, value = "addColor") String color,
                                   @RequestParam(required = false, value = "addDescription") String description,
                                   @RequestParam(required = false, value = "addMaterial") String material,
                                   @RequestParam(required = false, value = "addProduct") int idProduct,
                                   Model model, RedirectAttributes redirectAttributes){

        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    ProductDetail productDetail = new ProductDetail();
                    productDetail.setBrand(brand);
                    productDetail.setColor(color);
                    productDetail.setDescription(description);
                    productDetail.setMaterial(material);
                    productDetail.setProduct(productService.getProductById(idProduct));
                    productDetailService.save(productDetail);
                    String message = "Add Product Detail Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Add Product Detail Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }


                return "redirect:/admin/list-productdetail/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value = {"/edit-productdetail"}, method = RequestMethod.POST)
    public String EditProductDetail(HttpServletRequest rq,
                                    @RequestParam(required = false, value = "editID") int id,
                                    @RequestParam(required = false, value = "editBrand") String brand,
                                    @RequestParam(required = false, value = "editColor") String color,
                                    @RequestParam(required = false, value = "editDescription") String description,
                                    @RequestParam(required = false, value = "editMaterial") String material,
                                    Model model, RedirectAttributes redirectAttributes){
        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    ProductDetail productDetail = productDetailService.getProductDetailById(id);
                    productDetail.setBrand(brand);
                    productDetail.setColor(color);
                    productDetail.setDescription(description);
                    productDetail.setMaterial(material);

                    productDetailService.save(productDetail);
                    String message = "Edit Product Detail Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Edit Product Detail Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }

                return "redirect:/admin/list-productdetail/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value ="/delete-productdetail", method = RequestMethod.POST)
    public String deleteProductDetail(HttpServletRequest rq,
                                      @RequestParam(required = false, value = "deleteProductDetail") int id,
                                      @RequestParam(required = false, value = "result") String result,
                                      RedirectAttributes redirectAttributes){
        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    if (id != 0){
                        productDetailService.deleteProductDetail(id);
                    }else {
                        String[] listID = result.split(",");
                        for (String idC : listID){
                            productDetailService.deleteProductDetail(Integer.parseInt(idC));
                        }
                    }

                    String message = "Delete Product Detail Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Delete Product Detail Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                    e.printStackTrace();
                }

                return "redirect:/admin/list-productdetail/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }

    }

    @RequestMapping(value = {"/list-bill/{pagenumber}"})
    public String ListAllBill(HttpServletRequest rq,
                              @PathVariable("pagenumber") Integer pagenumber,
                              Model model){

        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                String indexPage = String.valueOf(pagenumber);
                if (indexPage == null) {
                    indexPage = "1";
                }

                List<Bill> listAllBill = billService.findAll();
                //System.out.println("name" + listAllCategory.get(0).getName());

                int count = listAllBill.size();
                int index = Integer.parseInt(indexPage);

                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage = endPage + 1;
                }
//                List<Bill> listBillPaging = billService.PagingAllBillByStatus(pagenumber -1 , PAGE_SITE_B, 1).getContent();
                List<Bill> listBillPaging = billService.PagingAllBill(pagenumber -1 , PAGE_SITE_B).getContent();
                List<User> listAllUser = userService.findUserByRole(1);
                model.addAttribute("endP", endPage);
                model.addAttribute("tag", index);
                model.addAttribute("listAllBill", listBillPaging);
                model.addAttribute("listAllUser", listAllUser);
                model.addAttribute("counts", count);
                //model.addAttribute("check", check);
                return "admin/list-bill";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }
    @RequestMapping(value = {"/add-bill"}, method = RequestMethod.POST)
    public String AddBill(HttpServletRequest rq,
                          @RequestParam(required = false, value = "addName") int idUser,
                          @RequestParam(required = false, value = "addDate") Date date,
                          @RequestParam(required = false, value = "addTotal") double total,
                          @RequestParam(required = false, value = "addStt") int status,
                          Model model, RedirectAttributes redirectAttributes){

        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    Bill bill = new Bill();
                    bill.setUser(userService.getUserById(idUser));
                    bill.setDate(date);
                    bill.setTotal(total);
                    bill.setStatus(status);

                    billService.saveBill(bill);
                    String message = "Add Bill Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Add Bill Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }


                return "redirect:/admin/list-bill/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value = {"/edit-bill"}, method = RequestMethod.POST)
    public String EditBill(HttpServletRequest rq,
                           @RequestParam(required = false, value = "editID") int id,
                           @RequestParam(required = false, value = "editName") String name,
                           @RequestParam(required = false, value = "editDate") Date date,
                           @RequestParam(required = false, value = "editTotal") double total,
                           @RequestParam(required = false, value = "editStt") int status,
                           Model model, RedirectAttributes redirectAttributes) {
        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    // Fetch and update product details based on your business logic
                    Bill bill = billService.getBillById(id);
//                    bill.setUser(name);
                    bill.setDate(date);
                    bill.setTotal(total);
                    bill.setStatus(status);
                    billService.saveBill(bill);

                    String message = "Edit Bill Success!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e) {
                    String messageError = "Edit Bill Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }

                return "redirect:/admin/list-bill/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

//    @RequestMapping(value = {"/edit-bill"}, method = RequestMethod.POST)
//    public String EditBill(HttpServletRequest rq,
//                           @RequestParam(required = false, value = "editID") int id,
//                           @RequestParam(required = false, value = "editBrand") String brand,
//                           @RequestParam(required = false, value = "editColor") String color,
//                           @RequestParam(required = false, value = "editDescription") String description,
//                           @RequestParam(required = false, value = "editMaterial") String material,
////                           @RequestParam(required = false, value = "editStatus") int status,
//                           Model model, RedirectAttributes redirectAttributes){
//        HttpSession session = rq.getSession();
//
//        if (session != null && session.getAttribute("account") != null) {
//            User user = (User) session.getAttribute("account");
//            if (user.getRole() == 3) {
//                try {
//                    ProductDetail productDetail = productDetailService.getProductDetailById(id);
//                    productDetailService.save(productDetail);
//                    String message = "Edit Bill Succes!!!";
//                    redirectAttributes.addFlashAttribute("message", message);
//                } catch (Exception e){
//                    String messageError = "Edit Bill Failed!!!";
//                    redirectAttributes.addFlashAttribute("messageError", messageError);
//                }
//
//                return "redirect:/admin/list-bill/1";
//            } else {
//                session.removeAttribute("account");
//                return "redirect:/admin/login";
//            }
//        } else {
//            return "redirect:/admin/login";
//        }
//    }

    @RequestMapping(value ="/delete-bill", method = RequestMethod.POST)
    public String deleteBill(HttpServletRequest rq,
                             @RequestParam(required = false, value = "deleteBill") int id,
                             @RequestParam(required = false, value = "result") String result,
                             RedirectAttributes redirectAttributes){
        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    if (id != 0){
                        billService.deleteBill(id);
                    }else {
                        String[] listID = result.split(",");
                        for (String idC : listID){
                            billService.deleteBill(Integer.parseInt(idC));
                        }
                    }

                    String message = "Delete Bill Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Delete Bill Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                    e.printStackTrace();
                }

                return "redirect:/admin/list-bill/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }

    }

    @RequestMapping(value = {"/list-billdetail/{pagenumber}"})
    public String ListAllBillDetail(HttpServletRequest rq,
                                    @PathVariable("pagenumber") Integer pagenumber,
                                    Model model){

        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                String indexPage = String.valueOf(pagenumber);
                if (indexPage == null) {
                    indexPage = "1";
                }

                List<BillDetail> billDetailList = billDetailService.findAll();
                //System.out.println("name" + listAllCategory.get(0).getName());

                int count = billDetailList.size();
                int index = Integer.parseInt(indexPage);

                int endPage = count / 10;
                if (count % 10 != 0) {
                    endPage = endPage + 1;
                }

                List<BillDetail> listBillPaging = billDetailService.PagingAllBillDetail(pagenumber -1 , PAGE_SITE_B).getContent();
                List<Bill> listAllBill = billService.findAll();
                List<Product> listAllProduct = productService.findAll();
                model.addAttribute("endP", endPage);
                model.addAttribute("tag", index);
                model.addAttribute("listAllBillDetail", listBillPaging);
                model.addAttribute("listAllBill", listAllBill);
                model.addAttribute("listAllProduct", listAllProduct);
                model.addAttribute("counts", count);
                //model.addAttribute("check", check);
                return "admin/list-billdetail";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value = {"/add-billdetail"}, method = RequestMethod.POST)
    public String AddBillDetail(HttpServletRequest rq,
                                @RequestParam(required = false, value = "addBill") int idBill,
                                @RequestParam(required = false, value = "addProduct") String idProduct,
                                @RequestParam(required = false, value = "addQuantity") int quantity,
                                Model model, RedirectAttributes redirectAttributes){

        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    BillDetail billDetail = new BillDetail();
                    String[] listP = idProduct.split(",");
                    Product product = productService.getProductById(Integer.valueOf(listP[0]));
                    Bill bill = billService.getBillById(idBill);
                    int amount = product.getAmount();
                    product.setAmount(amount + quantity);
                    billDetail.setBillId(bill);
                    billDetail.setProductId(product);
                    billDetail.setQuantity(quantity);
                    //List<BillDetail> billDetailList = billDetailService.findAllByBillId(bill);
                    double total = bill.getTotal();

                    bill.setTotal(total + (quantity * product.getPrice()));

                    billDetailService.saveBillDetail(billDetail);
                    String message = "Add Bill Detail Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Add Bill Detail Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }


                return "redirect:/admin/list-billdetail/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value = {"/edit-billdetail"}, method = RequestMethod.POST)
    public String EditBillDetail(HttpServletRequest rq,
                                 @RequestParam(required = false, value = "editBill") int bill,
                                 @RequestParam(required = false, value = "editProduct") String product,
                                 @RequestParam(required = false, value = "editQuantity") int quantity,
                                 @RequestParam(required = false, value = "editProductID") int productID,
                                 Model model, RedirectAttributes redirectAttributes){
        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    Optional<BillDetail> billDetail = billDetailService.findBillDetailsByProductIdAndBillId(productService.getProductById(productID), billService.getBillById(bill));
                    Product product1 = productService.getProductById(productID);
                    int amount = product1.getAmount();
                    if(billDetail.get().getQuantity() > quantity){
                        product1.setAmount(amount - (billDetail.get().getQuantity() - quantity));
                    } else {
                        product1.setAmount(amount + (quantity - billDetail.get().getQuantity()));
                    }
                    billDetail.get().setQuantity(quantity);

                    billDetailService.saveBillDetail(billDetail.get());
                    String message = "Edit Bill Detail Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Edit Bill Detail Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                }

                return "redirect:/admin/list-billdetail/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }
    }

    @RequestMapping(value ="/delete-billdetail", method = RequestMethod.POST)
    public String deleteBillDetail(HttpServletRequest rq,
                                   @RequestParam(required = false, value = "deleteBillDetail") int idBill,
                                   @RequestParam(required = false, value = "deleteProductDetail") int idProduct,
                                   @RequestParam(required = false, value = "result") String result,
                                   RedirectAttributes redirectAttributes){
        HttpSession session = rq.getSession();

        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");
            if (user.getRole() == 3) {
                try {
                    if (idBill != 0 && idProduct != 0){
                        billDetailService.deleteBillDetail(billService.getBillById(idBill), productService.getProductById(idProduct));
                    }else {
                        System.out.println("Đây" + result);
                        String[] listAllID = result.split(",");
                        for (String idBP : listAllID){
                            String[] listID = idBP.split(":");
                            System.out.println("Đây" + idBP);
                            billDetailService.deleteBillDetail(billService.getBillById(Integer.parseInt(listID[0])), productService.getProductById(Integer.parseInt(listID[1])));
                        }
                    }

                    String message = "Delete Bill Detail Succes!!!";
                    redirectAttributes.addFlashAttribute("message", message);
                } catch (Exception e){
                    String messageError = "Delete Bill Detail Failed!!!";
                    redirectAttributes.addFlashAttribute("messageError", messageError);
                    e.printStackTrace();
                }

                return "redirect:/admin/list-billdetail/1";
            } else {
                session.removeAttribute("account");
                return "redirect:/admin/login";
            }
        } else {
            return "redirect:/admin/login";
        }

    }
}