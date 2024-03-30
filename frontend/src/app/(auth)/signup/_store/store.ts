import { create } from 'zustand';
import { RegisterDataType } from '../_types/types';

const useRegisterStore = create<Model>((set) => ({
  registerData: {
    name: '',
    gender: '',
    birth: '',
    telephone: '',
    address: '',
    address2: '',
    email: '',
    password: '',
  } as RegisterDataType,

  setRegisterData: (registerData: RegisterDataType) =>
    set(() => ({ registerData })),
}));

interface Model {
  registerData: RegisterDataType;
  setRegisterData: (registerData: RegisterDataType) => void;
}

export default useRegisterStore;
