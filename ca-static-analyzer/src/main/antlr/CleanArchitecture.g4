grammar CleanArchitecture;

unit_declaration
    :   '/**' CLEAN_ARCHITECTURE_UNIT '*/'
    ;
CLEAN_ARCHITECTURE_UNIT
    :   '@Entity'
    |   '@UseCase'
    |   '@InterfaceAdapter'
    |   '@Framework'
    ;
