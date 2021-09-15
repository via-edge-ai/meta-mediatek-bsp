inherit image_types

CONVERSIONTYPES += "img"
CONVERSION_CMD:img = " \
    truncate -s%4096 "${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}"; \
    img2simg -b 4096 \
             -o "${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}.img" \
             "${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type}"; \
"
CONVERSION_DEPENDS_img = "python3-pysimg-native"
