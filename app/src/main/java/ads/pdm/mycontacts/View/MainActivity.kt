package ads.pdm.mycontacts.View

import ads.pdm.mycontacts.Model.Constant.EXTRA_CONTACT
import ads.pdm.mycontacts.Model.Contact
import ads.pdm.mycontacts.R
import ads.pdm.mycontacts.databinding.ActivityMainBinding
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

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

    private lateinit var carl: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        fillContactList()
        amb.contactsLv.adapter = contactAdapter

        carl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
        ) {
            result ->
            if(result.resultCode== RESULT_OK){
                val contact = result.data?.getParcelableExtra<Contact>(EXTRA_CONTACT)
                contact?.let {
                    _contact->
                    contactList.add(_contact)
                    contactAdapter.add(_contact.toString())
                    contactAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.addContactMi -> {
                //abre a tela de contato
                carl.launch(Intent(this, Contact::class.java))
                true
            }else -> { false }
        }
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