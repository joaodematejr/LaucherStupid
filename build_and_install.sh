#!/bin/bash

# Script para build e instalação do Launcher no POS
# Usage: ./build_and_install.sh [l3|l4] [debug|release]

MODEL=${1:-l4}
BUILD_TYPE=${2:-debug}

echo "🔨 Building Launcher $MODEL $BUILD_TYPE..."

# Limpar projeto
./gradlew clean

# Build do APK
if [ "$BUILD_TYPE" = "release" ]; then
    ./gradlew assemble${MODEL^}Release
    APK_PATH="app/build/outputs/apk/$MODEL/release/app-$MODEL-release.apk"
else
    ./gradlew assemble${MODEL^}Debug
    APK_PATH="app/build/outputs/apk/$MODEL/debug/app-$MODEL-debug.apk"
fi

# Verificar se o build foi bem-sucedido
if [ -f "$APK_PATH" ]; then
    echo "✅ Build concluído: $APK_PATH"

    # Verificar se há dispositivo conectado
    DEVICE_COUNT=$(adb devices | grep -v "List of devices" | grep -c "device")

    if [ "$DEVICE_COUNT" -gt 0 ]; then
        echo "📱 Instalando no dispositivo POS..."

        # Tentar instalação normal primeiro
        if adb install -r "$APK_PATH"; then
            echo "✅ Instalação concluída com sucesso!"
        else
            echo "⚠️  Instalação normal falhou, tentando com force..."
            # Tentar com force se falhar
            if adb install -r -d "$APK_PATH"; then
                echo "✅ Instalação concluída com force!"
            else
                echo "❌ Falha na instalação. Verifique as configurações do POS."
                echo "💡 Dicas:"
                echo "   - Verificar se 'Fontes desconhecidas' está habilitado"
                echo "   - Verificar se USB debugging está ativo"
                echo "   - Tentar: adb install -r -t $APK_PATH"
            fi
        fi
    else
        echo "⚠️  Nenhum dispositivo Android conectado"
        echo "💡 Conecte o POS via USB e habilite USB debugging"
    fi
else
    echo "❌ Falha no build do APK"
    echo "💡 Verifique:"
    echo "   - Se o arquivo platform.jks está na raiz do projeto"
    echo "   - Se o arquivo keystore.properties existe e está correto"
    echo "   - Se não há erros de compilação"
fi
