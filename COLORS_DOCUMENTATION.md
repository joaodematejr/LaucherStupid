# 🎨 Esquema de Cores - Launcher

## 📋 Resumo

## 🌈 Paleta Principal

### **Cores Primárias** (Azul Profissional)

- **PrimaryBlue**: `#1E3A8A` - Azul escuro profissional para elementos principais
- **PrimaryBlueLight**: `#3B82F6` - Azul claro vibrante
- **PrimaryBluePale**: `#DEEAFF` - Azul muito claro para containers

### **Cores Secundárias** (Verde Transações)

- **SuccessGreen**: `#059669` - Verde escuro para transações aprovadas
- **SuccessGreenLight**: `#10B981` - Verde claro
- **SuccessGreenPale**: `#D1FAE5` - Verde muito claro

### **Cores de Destaque** (Laranja Ações)

- **AccentOrange**: `#EA580C` - Laranja escuro para ações importantes
- **AccentOrangeLight**: `#F97316` - Laranja claro
- **AccentOrangePale**: `#FED7AA` - Laranja muito claro

## 🎯 Cores por Funcionalidade

### **💳 Transações e Pagamentos**

```kotlin
transactionSuccess: SuccessGreen      // ✅ Transação aprovada
transactionPending: WarningYellow     // ⏳ Transação pendente  
transactionFailed: ErrorRed           // ❌ Transação negada
transactionRefund: InfoCyan           // 🔄 Estorno
cardChip: Color(0xFFFFD700)          // 💛 Chip dourado
cardMagnetic: NeutralGray800          // ⬛ Tarja magnética
cardContactless: SystemBlue           // 📱 Contactless
```

### **⚙️ Sistema e Hardware**

```kotlin
systemOnline: SuccessGreen            // 🟢 Sistema online
systemOffline: ErrorRed               // 🔴 Sistema offline
systemProcessing: WarningYellow       // 🟡 Processando
batteryFull: BatteryGreen             // 🔋 Bateria cheia
batteryMedium: WarningYellow          // 🟡 Bateria média
batteryLow: AccentOrange              // 🟠 Bateria baixa
batteryCritical: ErrorRed             // 🔴 Bateria crítica
```

### **📡 Comunicação**

```kotlin
wifiConnected: SuccessGreen           // 📶 WiFi conectado
wifiDisconnected: NeutralGray400      // 📶 WiFi desconectado
bluetoothConnected: CommunicationPurple // 🔵 Bluetooth conectado
bluetoothDisconnected: NeutralGray400 // ⚫ Bluetooth desconectado
gprsConnected: InfoCyan               // 📱 GPRS conectado
gprsDisconnected: NeutralGray400      // ⚫ GPRS desconectado
gpsActive: SuccessGreen               // 🌍 GPS ativo
gpsInactive: NeutralGray400           // ⚫ GPS inativo
```

### **💻 Especificações Técnicas**

```kotlin
processorNormal: SuccessGreen         // 🖥️ Processador normal
processorHigh: WarningYellow          // ⚠️ Processador alta carga
memoryAvailable: SuccessGreen         // 💾 Memória disponível
memoryUsed: AccentOrange              // 📊 Memória usada
displayActive: SystemBlue             // 📺 Display ativo
displayInactive: NeutralGray400       // ⚫ Display inativo
```

## 🌙☀️ Temas Dark/Light

### **Tema Claro**

- Fundo principal: Branco puro (`#FFFFFF`)
- Superfícies: Cinza super claro (`#FAFAFA`)
- Textos: Cinza escuro (`#111827`)
- Alto contraste para legibilidade

### **Tema Escuro**

- Fundo principal: Azul escuro (`#0F172A`)
- Superfícies: Cinza escuro (`#1E293B`)
- Textos: Cinza claro (`#F3F4F6`)
- Cores mais suaves para os olhos

## 📱 Como Usar

### **1. Cores do Material 3**

```kotlin
// Cores padrão do tema
MaterialTheme.colorScheme.primary
MaterialTheme.colorScheme.secondary
MaterialTheme.colorScheme.background
MaterialTheme.colorScheme.surface
```

### **2. Cores Personalizadas do Launcher**

```kotlin
// Cores específicas do launcher
MaterialTheme.colorScheme.launcher.transactionSuccess
MaterialTheme.colorScheme.launcher.batteryFull
MaterialTheme.colorScheme.launcher.wifiConnected
MaterialTheme.colorScheme.launcher.splashGradientStart
```

### **3. Exemplo Prático**

```kotlin
@Composable
fun TransactionCard(isSuccess: Boolean) {
    val launcher = MaterialTheme.colorScheme.launcher
    val statusColor = if (isSuccess) 
        launcher.transactionSuccess 
    else 
        launcher.transactionFailed
    
    Card(
        colors = CardDefaults.cardColors(
            containerColor = statusColor.copy(alpha = 0.1f)
        )
    ) {
        // Conteúdo do card
    }
}
```

## 🎨 Paleta Completa

### **Status e Estados**

- ✅ **Sucesso**: Verde (`#059669`)
- ⚠️ **Aviso**: Amarelo (`#D97706`)
- ❌ **Erro**: Vermelho (`#DC2626`)
- ℹ️ **Info**: Ciano (`#0891B2`)

### **Interface**

- 🌅 **Gradiente Splash**: Azul escuro → Azul claro
- 🎴 **Cards**: Cinza claro com elevação
- ➖ **Divisores**: Cinza médio
- 🌫️ **Overlay**: Preto 50% transparência

### **Cinzas Neutros** (9 tons)

De quase preto (`#111827`) até quase branco (`#F9FAFB`)

## ✨ Benefícios

1. **🎯 Específico para POS**: Cores pensadas para transações e hardware
2. **♿ Acessível**: Alto contraste e legibilidade
3. **🌙 Dark/Light**: Suporte completo aos dois temas
4. **📱 Material 3**: Compatível com as diretrizes do Google
5. **🔧 Extensível**: Fácil de adicionar novas cores
6. **👁️ Profissional**: Visual moderno e confiável

## 📁 Arquivos Criados

- `Color.kt` - Definições de todas as cores
- `LauncherColors.kt` - Cores específicas do launcher
- `Theme.kt` - Esquemas de cores Material 3
- `ColorExamples.kt` - Exemplos de uso e previews

**Agora seu launcher terá um visual profissional e moderno! 🚀**
