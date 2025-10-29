# Launcher Stupid - Android Launcher Personalizado

Um launcher Android personalizado desenvolvido em Kotlin com Jetpack Compose.

## 📱 Sobre o Projeto

Este é um launcher Android que substitui a tela inicial padrão do seu dispositivo, permitindo uma
experiência personalizada de navegação e organização de aplicativos.

## 🏷️ Product Flavors

O projeto está configurado com **Product Flavors** para diferentes modelos:

### **L3 Launcher**

- **Modelo**: L3
- **App ID**: `com.demate.laucherstupid.l3`
- **Nome**: "Launcher L3"
- **Grid**: 3 colunas
- **Ícones**: 56dp
- **Premium**: Desabilitado

## 🚀 Funcionalidades

- **Interface Personalizada**: Tela inicial com grid de aplicativos
- **Listagem de Apps**: Mostra todos os aplicativos instalados no dispositivo
- **Lançamento de Apps**: Permite abrir qualquer aplicativo instalado
- **Design Moderno**: Interface construída com Jetpack Compose
- **Launcher Principal**: Pode ser definido como launcher padrão do sistema

## 🛠️ Tecnologias Utilizadas

- **Kotlin 2.2.21** - Linguagem principal
- **Jetpack Compose (BOM 2025.10.01)** - UI moderna
- **Android 16 (API 36)** - Target SDK mais recente
- **Android Gradle Plugin 8.13.0** - Build tools
- **Java 17** - Runtime

## 📋 Pré-requisitos

- Android Studio Ladybug 2024.2.1+ ou superior
- Android SDK API 25+ (Android 7.1)
- Gradle 8.14.3+
- JDK 17+

## 🔧 Instalação

1. Clone o repositório:
```bash
git clone https://github.com/joaodematejr/LaucherStupid.git
```

2. Abra o projeto no Android Studio

3. Sincronize o projeto com os arquivos Gradle

4. Execute o build (use comandos com flavor L3):
```bash
# Build completo
./gradlew assembleL3Debug

# Ou use o script helper
chmod +x gradle_tasks_l3.sh
./gradle_tasks_l3.sh
```

## 🏃‍♂️ Como Executar

1. Conecte um dispositivo Android ou inicie um emulador

2. Execute o app L3:

```bash
./gradlew installL3Debug
```

## 📋 Comandos Gradle (L3 Flavor)

### **Build Tasks**:
```bash
./gradlew assembleL3Debug     # APK Debug
./gradlew assembleL3Release   # APK Release
```

### **Test Tasks**:

```bash
./gradlew lintL3Debug         # Análise de código
./gradlew testL3DebugUnitTest # Testes unitários
```

### **Install Tasks**:

```bash
./gradlew installL3Debug     # Instalar debug
./gradlew uninstallL3Debug   # Desinstalar debug
```

⚠️ **Importante**: Use sempre os comandos com sufixo `L3` devido aos product flavors configurados!

## 📁 Estrutura de Saída

```
app/build/outputs/apk/l3/
├── debug/
│   └── app-l3-debug.apk
└── release/
    └── app-l3-release.apk
```

## 🎨 Esquema de Cores

O projeto inclui um esquema de cores profissional otimizado para launchers de pagamento:

- **Tema Claro/Escuro**: Suporte completo
- **Cores específicas**: Transações, sistema, comunicação
- **Material 3**: Design system moderno

Veja `COLORS_DOCUMENTATION.md` para detalhes completos.

## 📱 Como Definir como Launcher Principal

### Método 1: Através das Configurações do Android

1. Instale o app no seu dispositivo
2. Vá em **Configurações** > **Apps** > **Apps padrão**
3. Selecione **App inicial** ou **Launcher**
4. Escolha **Launcher Stupid** na lista

### Método 2: Através do Botão Home

1. Instale o app
2. Pressione o botão **Home** do dispositivo
3. O sistema perguntará qual launcher usar
4. Selecione **Launcher Stupid**
5. Escolha **Sempre** para torná-lo padrão

### Método 3: Para Desenvolvedores (ADB)

```bash
# Definir como launcher padrão via ADB
adb shell cmd package set-home-activity com.demate.laucherstupid/.MainActivity
```

## 🎨 Personalização

O launcher possui as seguintes características:

- **Grid 4x4**: Aplicativos organizados em grade de 4 colunas
- **Ícones 48dp**: Tamanho padrão para boa visualização
- **Nomes dos Apps**: Máximo de 2 linhas por nome
- **Material Design 3**: Segue as diretrizes do Material Design

## 🔒 Permissões Utilizadas

- `SET_WALLPAPER`: Para definir papéis de parede
- `SET_WALLPAPER_HINTS`: Para configurar dicas de wallpaper
- `REORDER_TASKS`: Para reordenar tarefas
- `KILL_BACKGROUND_PROCESSES`: Para gerenciar processos
- `VIBRATE`: Para feedback tátil

## 📁 Estrutura do Projeto

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/demate/laucherstupid/
│   │   │   ├── MainActivity.kt          # Activity principal do launcher
│   │   │   └── ui/theme/               # Tema e cores
│   │   ├── res/                        # Recursos (ícones, strings, etc.)
│   │   └── AndroidManifest.xml         # Configurações do launcher
│   ├── test/                           # Testes unitários
│   └── androidTest/                    # Testes instrumentados
├── build.gradle.kts                    # Configurações do build
└── proguard-rules.pro                  # Regras de ofuscação
```

## 🧪 Testes

Execute os testes unitários:

```bash
./gradlew test
```

Execute os testes instrumentados:

```bash
./gradlew connectedAndroidTest
```

## 📦 Build de Produção

Para gerar o APK de produção:

```bash
./gradlew assembleRelease
```

## 🔄 Voltar ao Launcher Original

Se quiser voltar ao launcher original do sistema:

1. Vá em **Configurações** > **Apps** > **Apps padrão**
2. Selecione **App inicial** ou **Launcher**
3. Escolha o launcher original (ex: "Pixel Launcher", "OneUI Home", etc.)

Ou via ADB:

```bash
adb shell cmd package clear-default-home
```

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas mudanças (`git commit -am 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

## 👨‍💻 Autor

- **João Dematé Jr** - [@joaodematejr](https://github.com/joaodematejr)

## 📞 Suporte

Se você encontrar algum problema ou tiver sugestões, por favor abra
uma [issue](https://github.com/joaodematejr/LaucherStupid/issues).

## ⚠️ Aviso

Este launcher é um projeto educacional/experimental. Use por sua própria conta e risco.
Certifique-se de saber como voltar ao launcher original antes de definir este como padrão.
