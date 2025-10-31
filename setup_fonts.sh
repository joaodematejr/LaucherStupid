#!/bin/bash

# =============================================================================
# SCRIPT DE DOWNLOAD E CONFIGURAÇÃO DE FONTES
# Launcher de Pagamento L3
# =============================================================================

echo "🎨 CONFIGURANDO FONTES DO LAUNCHER DE PAGAMENTO"
echo "================================================="

# Criar diretório de fontes
FONT_DIR="app/src/main/res/font"
mkdir -p "$FONT_DIR"

echo ""
echo "📁 Criando diretório: $FONT_DIR"

# =============================================================================
# INSTRUÇÕES DE DOWNLOAD
# =============================================================================

echo ""
echo "📥 INSTRUÇÕES DE DOWNLOAD:"
echo ""
echo "1️⃣  INTER FONT (Fonte Principal)"
echo "   🔗 https://rsms.me/inter/"
echo "   📦 Baixe o arquivo ZIP"
echo "   📂 Extraia os arquivos .ttf"
echo ""
echo "2️⃣  JETBRAINS MONO (Fonte para Números)"
echo "   🔗 https://www.jetbrains.com/lp/mono/"
echo "   📦 Baixe o arquivo ZIP"
echo "   📂 Extraia os arquivos .ttf"

# =============================================================================
# NOMES DOS ARQUIVOS NECESSÁRIOS
# =============================================================================

echo ""
echo "📋 ARQUIVOS NECESSÁRIOS:"
echo ""
echo "📝 Inter (renomeie para):"
echo "   Inter-Thin.ttf           → inter_100_thin.ttf"
echo "   Inter-ExtraLight.ttf     → inter_200_extralight.ttf"
echo "   Inter-Light.ttf          → inter_300_light.ttf"
echo "   Inter-Regular.ttf        → inter_400_regular.ttf"
echo "   Inter-Medium.ttf         → inter_500_medium.ttf"
echo "   Inter-SemiBold.ttf       → inter_600_semibold.ttf"
echo "   Inter-Bold.ttf           → inter_700_bold.ttf"
echo "   Inter-ExtraBold.ttf      → inter_800_extrabold.ttf"
echo "   Inter-Black.ttf          → inter_900_black.ttf"
echo ""
echo "💻 JetBrains Mono (renomeie para):"
echo "   JetBrainsMono-Regular.ttf → jetbrains_mono_400_regular.ttf"
echo "   JetBrainsMono-Medium.ttf  → jetbrains_mono_500_medium.ttf"
echo "   JetBrainsMono-Bold.ttf    → jetbrains_mono_700_bold.ttf"

# =============================================================================
# COMANDO DE RENOMEAÇÃO (se os arquivos estiverem na pasta atual)
# =============================================================================

echo ""
echo "🔄 COMANDOS DE RENOMEAÇÃO:"
echo ""
echo "# Se você baixou as fontes na pasta atual, execute:"
echo ""
echo "# Inter"
echo "mv Inter-Thin.ttf $FONT_DIR/inter_100_thin.ttf 2>/dev/null || true"
echo "mv Inter-ExtraLight.ttf $FONT_DIR/inter_200_extralight.ttf 2>/dev/null || true"
echo "mv Inter-Light.ttf $FONT_DIR/inter_300_light.ttf 2>/dev/null || true"
echo "mv Inter-Regular.ttf $FONT_DIR/inter_400_regular.ttf 2>/dev/null || true"
echo "mv Inter-Medium.ttf $FONT_DIR/inter_500_medium.ttf 2>/dev/null || true"
echo "mv Inter-SemiBold.ttf $FONT_DIR/inter_600_semibold.ttf 2>/dev/null || true"
echo "mv Inter-Bold.ttf $FONT_DIR/inter_700_bold.ttf 2>/dev/null || true"
echo "mv Inter-ExtraBold.ttf $FONT_DIR/inter_800_extrabold.ttf 2>/dev/null || true"
echo "mv Inter-Black.ttf $FONT_DIR/inter_900_black.ttf 2>/dev/null || true"
echo ""
echo "# JetBrains Mono"
echo "mv JetBrainsMono-Regular.ttf $FONT_DIR/jetbrains_mono_400_regular.ttf 2>/dev/null || true"
echo "mv JetBrainsMono-Medium.ttf $FONT_DIR/jetbrains_mono_500_medium.ttf 2>/dev/null || true"
echo "mv JetBrainsMono-Bold.ttf $FONT_DIR/jetbrains_mono_700_bold.ttf 2>/dev/null || true"

# =============================================================================
# VERIFICAR ARQUIVOS EXISTENTES
# =============================================================================

echo ""
echo "🔍 VERIFICANDO ARQUIVOS EXISTENTES:"
echo ""

INTER_FILES=(
    "inter_100_thin.ttf"
    "inter_200_extralight.ttf"
    "inter_300_light.ttf"
    "inter_400_regular.ttf"
    "inter_500_medium.ttf"
    "inter_600_semibold.ttf"
    "inter_700_bold.ttf"
    "inter_800_extrabold.ttf"
    "inter_900_black.ttf"
)

MONO_FILES=(
    "jetbrains_mono_400_regular.ttf"
    "jetbrains_mono_500_medium.ttf"
    "jetbrains_mono_700_bold.ttf"
)

INTER_COUNT=0
MONO_COUNT=0

echo "📝 Inter:"
for file in "${INTER_FILES[@]}"; do
    if [ -f "$FONT_DIR/$file" ]; then
        echo "   ✅ $file"
        ((INTER_COUNT++))
    else
        echo "   ❌ $file (faltando)"
    fi
done

echo ""
echo "💻 JetBrains Mono:"
for file in "${MONO_FILES[@]}"; do
    if [ -f "$FONT_DIR/$file" ]; then
        echo "   ✅ $file"
        ((MONO_COUNT++))
    else
        echo "   ❌ $file (faltando)"
    fi
done

# =============================================================================
# STATUS FINAL
# =============================================================================

echo ""
echo "📊 STATUS:"
echo "   Inter: $INTER_COUNT/9 arquivos"
echo "   JetBrains Mono: $MONO_COUNT/3 arquivos"

if [ $INTER_COUNT -eq 9 ] && [ $MONO_COUNT -eq 3 ]; then
    echo ""
    echo "🎉 TODAS AS FONTES CONFIGURADAS!"
    echo "✅ Execute: ./gradlew clean assembleL3Debug"
    echo "✅ Descomente as linhas em TypographyWithFonts.kt"
    echo "✅ Use TypographyWithFonts em vez de Typography no Theme.kt"
else
    echo ""
    echo "⚠️  FONTES FALTANDO!"
    echo "📥 Baixe as fontes e renomeie conforme instruções acima"
fi

echo ""
echo "💡 DICA: Execute este script novamente após adicionar as fontes"
echo "================================================="
