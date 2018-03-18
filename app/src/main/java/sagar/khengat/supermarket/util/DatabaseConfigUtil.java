package sagar.khengat.supermarket.util;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

import sagar.khengat.supermarket.model.Cart;
import sagar.khengat.supermarket.model.Customer;
import sagar.khengat.supermarket.model.Gender;
import sagar.khengat.supermarket.model.History;
import sagar.khengat.supermarket.model.Product;


/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil
{
	
	
	@SuppressWarnings("rawtypes")
	static Class[] classes = new Class[]{Customer.class,Product.class, Cart.class, History.class,Gender.class};
	
	public static void main(String[] args) throws SQLException, IOException {
		writeConfigFile("ormlite_config.txt",classes);
	}

}
