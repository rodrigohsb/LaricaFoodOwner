package com.laricafood.owner.app.teste;

import android.content.Context;

import com.laricafood.owner.app.R;
import com.laricafood.owner.app.bean.Category;
import com.laricafood.owner.app.bean.Larica;
import com.laricafood.owner.app.bean.Message;
import com.laricafood.owner.app.bean.Type;
import com.laricafood.owner.app.bean.User;
import com.laricafood.owner.app.persistence.MessageRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Rodrigo on 26/06/15.
 */
public class Teste
{

    static User user = new User("10153016626303831", "Rodrigo", "Haus", "Bacellar", "https://graph.facebook.com/10153016626303831/picture?height=130&width=130&migration_overrides=%7Boctober_2012%3Atrue%7D", Type.COMERCIANTE, new Date(), new Date(), 0, getEstabelecimentos());

    public static List<Larica> getEstabelecimentos ()
    {
        List<Larica> laricaList = new ArrayList<>();

        //        Larica (int id, String nome, String endereco, String number, String bairro, String uf, double distance, boolean isOpen, int like, User user, String logo, String photo1, String photo2, String photo3, String photo4, int status)

        Larica larica = new Larica(1, "Mc Roger", "Praia de Botafogo", "340", "Botafogo", "MG", 1.23, true, 45, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 0);
        Larica larica1 = new Larica(2, "Michelle", "Rua Dias da Cruz", " 234", "Méier", "RS", 0.45, true, 92, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 0);
        Larica larica2 = new Larica(3, "Churrasquinho do Zé", "Rua Doutor Leal", "5432", "Eng. de Dentro", "PR", 0.34, true, 100, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 1);
        Larica larica3 = new Larica(4, "FaceBurguer", "Av. Brasil", "34535", "Irajá", "RR", 6.99, true, 34, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 0);
        Larica larica4 = new Larica(5, "Pizza da Maria", "Av. Pres Vargas", "234", "Centro", "RO", 3.5, true, 11, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 0);
        Larica larica5 = new Larica(6, "Sorvete Caseiro", "Rua dos Burros", "0", " Cascadura", "SP", 0.65, true, 7, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 1);
        Larica larica6 = new Larica(7, "Tapioca da Ana", "Rua Canavieiras", "219", "Grajaú", "BA", 0.57, true, 65, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 1);
        Larica larica7 = new Larica(8, "Mc Roger", "Praia de Botafogo", "340", "Botafogo", "CE", 0.9, true, 50, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 1);
        Larica larica8 = new Larica(9, "Michelle", "Rua Dias da Cruz", "234", "Méier", "AM", 1.56, true, 90, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 0);
        Larica larica9 = new Larica(10, "Churrasquinho do Zé", "Rua Doutor Leal", "5432", "Eng. de Dentro", "RO", 3.45, false, 35, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 0);
        Larica larica10 = new Larica(11, "FaceBurguer", "Av. Brasil", "34535", "Irajá", "RJ", 9.00, false, 10, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 0);
        Larica larica11 = new Larica(12, "Pizza da Maria", "Av. Pres Vargas", "234", "Centro", "SP", 0.54, false, 22, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 0);
        Larica larica12 = new Larica(13, "Sorvete Caseiro", "Rua dos Burros", "0", " Cascadura", "SC", 0.1, false, 68, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 1);
        Larica larica13 = new Larica(14, "Tapioca da Ana", "Rua Canavieiras", "219", "Grajaú", "GO", 2.35, false, 84, Teste.user, "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", "http://www.olharturistico.com.br/wp-content/uploads/2014/04/Buzina-Food-Truck.jpg", 0);


        laricaList.add(larica);
        laricaList.add(larica1);
        laricaList.add(larica2);
        laricaList.add(larica3);
        laricaList.add(larica4);
        laricaList.add(larica5);
        laricaList.add(larica6);
        laricaList.add(larica7);
        laricaList.add(larica8);
        laricaList.add(larica9);
        laricaList.add(larica10);
        laricaList.add(larica11);
        laricaList.add(larica12);
        laricaList.add(larica13);

        return laricaList;

    }

    public static void createMessages (Context ctx)
    {
        List<Message> messageList = new ArrayList<>();

        for (int i = 0 ; i < 15 ; i++)
        {
            Message m = new Message();
            m.setTitle("26 e 27/jun - Food Truck na Zn. Norte");
            m.setContent("Blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá" +
                    " blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá" +
                    " blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá" +
                    " blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá blá.");
            m.setDate(new Date());
            m.setUnread(true);
            messageList.add(m);
        }

        MessageRepository.getInstance(ctx).saveAll(messageList);
    }

    public static List<Category> getCategories ()
    {
        List<Category> categories = new ArrayList<>();

        Category c1 = new Category("Hamburguer", R.drawable.ic_home_hamburger);
        Category c2 = new Category("Cachorro - Quente", R.drawable.ic_home_hotdog);
        Category c3 = new Category("Churrasquinho", R.drawable.ic_home_churrasquinho);
        Category c4 = new Category("Pizza", R.drawable.ic_home_pizza);
        Category c5 = new Category("Saduíche Natural", R.drawable.ic_home_sanduiche_natural);
        Category c6 = new Category("Wrap", R.drawable.ic_home_wrap);
        Category c7 = new Category("Doce", R.drawable.ic_home_cake);
        Category c8 = new Category("Sorvete", R.drawable.ic_home_icecream);
        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
        categories.add(c4);
        categories.add(c5);
        categories.add(c6);
        categories.add(c7);
        categories.add(c8);
        //Category c9 = new Category("Milho", R.drawable.ic_home_corn);
        //categories.add(c9);
        return categories;
    }

    public static User getUser ()
    {
        return Teste.user;

    }

}
