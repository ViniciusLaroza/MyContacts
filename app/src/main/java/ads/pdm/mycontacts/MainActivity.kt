package ads.pdm.mycontacts

import ads.pdm.mycontacts.Model.Contact
import ads.pdm.mycontacts.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    //Data source
    private val contactList: MutableList<Contact> = mutableListOf()

    //Adapter
    private val contactAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            contactList.map { it.toString() }
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        fillContactList()
        amb.contactsLv.adapter = contactAdapter
    }


    private fun fillContactList(){
        for (i in 1 .. 50){
            contactList.add(
                Contact(
                id = i,
                name = "Nome $i",
                adress = "Endereco $i",
                phone = "Telefone $i",
                email = "Email $i",
            )
            )
        }
    }
}