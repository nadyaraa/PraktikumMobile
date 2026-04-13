package com.example.tipapp_comp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipapp_comp.ui.theme.TipApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipApp {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TipAppComp()
                }
            }
        }
    }
}

@Composable
fun TipAppComp() {
    var billAmount by remember { mutableStateOf("") }
    var percentageTip by remember { mutableIntStateOf(15) }
    var roundUp by remember { mutableStateOf(false) }

    val tip = calculateTip(
        amount = billAmount,
        tipPercent = percentageTip,
        roundUp = roundUp
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = stringResource(R.string.title),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        EditNumberField(
            value = billAmount,
            onValueChange = { newValue ->
                billAmount = newValue
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        TipDropdown(
            percentageTip = percentageTip,
            onTipSelected = { newValue ->
                percentageTip = newValue
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        RoundUpTip(
            roundUp = roundUp,
            onRoundUpChanged = { newValue ->
                roundUp = newValue
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.formatTip, tip),
            style = MaterialTheme.typography.displaySmall,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun EditNumberField(
    value: String,
    onValueChange: (String) -> Unit
){
    TextField(
        label = { Text(text = stringResource(R.string.labelBill)) },
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_amount),
                modifier = Modifier.size(24.dp),
                contentDescription = stringResource(R.string.contDescIconBill)
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        modifier = Modifier.fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipDropdown(
    percentageTip: Int,
    onTipSelected: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val tipOptions = listOf(15, 18, 20)

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { newValue ->
            expanded = newValue
        }
    ) {

        TextField(
            value = "$percentageTip%",
            onValueChange = {},
            readOnly = true,
            label = { Text(stringResource(R.string.labelDropdown)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_percent),
                    modifier = Modifier.size(24.dp),
                    contentDescription = stringResource(R.string.contDescIconPercent)
                )
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            tipOptions.forEach { tip ->
                DropdownMenuItem(
                    text = { Text("$tip%") },
                    onClick = {
                        onTipSelected(tip)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun RoundUpTip(
    roundUp: Boolean,
    onRoundUpChanged: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.labelRoundup),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChanged
        )
    }
}

fun calculateTip(
    amount: String,
    tipPercent: Int,
    roundUp: Boolean
): Double {
    val bill = amount.toDoubleOrNull() ?: 0.0
    var tip = bill * tipPercent / 100

    if (roundUp) {
        tip = kotlin.math.ceil(tip)
    }

    return tip
}