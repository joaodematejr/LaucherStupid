# 🔒 Configuração de Segurança para Assinatura de APK

## 📁 Arquivos Criados

### 1. `keystore.properties`

Arquivo com configurações de assinatura (NÃO COMMITADO)

```properties
storeFile=platform.jks
storePasswordRelease=""
storePasswordDebug=""
keyAlias=platform
keyPasswordRelease=""
keyPasswordDebug=""
```

### 2. `.env`

Arquivo com variáveis de ambiente (NÃO COMMITADO)

```bash
KEYSTORE_FILE=platform.jks
KEYSTORE_PASSWORD_RELEASE=""
KEYSTORE_PASSWORD_DEBUG=""
KEY_ALIAS=platform
KEY_PASSWORD_RELEASE=""
KEY_PASSWORD_DEBUG=""
```

### 3. `.gitignore`

Garante que arquivos sensíveis não sejam commitados

## 🚀 Como Usar

### Opção 1: Usando keystore.properties (Recomendado)

1. Coloque o arquivo `platform.jks` na raiz do projeto
2. O `build.gradle.kts` já está configurado para ler o `keystore.properties`
3. Execute: `./gradlew assembleDebug` ou `./gradlew assembleRelease`

### Opção 2: Usando Variáveis de Ambiente

1. Execute: `source setup_env.sh`
2. Modifique o `build.gradle.kts` para usar a versão em `build_gradle_env_version.txt`
3. Execute: `./gradlew assembleDebug`

## 🔧 Comandos de Build

```bash
# Limpar projeto
./gradlew clean

# Build debug para L3
./gradlew assembleL3Debug

# Build debug para L4
./gradlew assembleL4Debug

# Build release para L3
./gradlew assembleL3Release

# Build release para L4
./gradlew assembleL4Release
```

## 📦 Instalação no POS

```bash
# Instalar APK L3 debug
adb install -r app/build/outputs/apk/l3/debug/app-l3-debug.apk

# Instalar APK L4 debug
adb install -r app/build/outputs/apk/l4/debug/app-l4-debug.apk

# Forçar instalação (se necessário)
adb install -r -d app/build/outputs/apk/l3/debug/app-l3-debug.apk
```

## ⚠️ Segurança

- ✅ `platform.jks` - Manter local, NÃO commitar
- ✅ `keystore.properties` - NÃO commitar (já no .gitignore)
- ✅ `.env` - NÃO commitar (já no .gitignore)
- ✅ Senhas ficam fora do código fonte
- ✅ Build funciona em diferentes ambientes

## 🔄 Para Equipe

Cada desenvolvedor deve:

1. Receber o arquivo `platform.jks` separadamente
2. Criar seu próprio `keystore.properties` local
3. Nunca commitar informações de assinatura

## 🐛 Troubleshooting

Se der erro `INSTALL_FAILED_VERIFICATION_FAILURE`:

1. Verifique se o `platform.jks` está na raiz do projeto
2. Confirme as senhas no `keystore.properties`
3. Use `adb install -r -d` para forçar instalação
4. Verifique se o POS permite instalação de apps desconhecidos
