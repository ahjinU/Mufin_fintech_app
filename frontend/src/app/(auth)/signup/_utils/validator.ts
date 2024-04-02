export const isValidName = (name: string) => {
  const nameRegex = /^[가-힣a-zA-Z]{2,6}$/;
  return nameRegex.test(name);
};

export const isValidYear = (year: string): boolean => {
  const yearRegex: RegExp = /^\d{4}$/;
  return yearRegex.test(year);
};

export const isValidMonth = (month: string): boolean => {
  const monthRegex: RegExp = /^([1-9]|0[1-9]|1[0-2])$/;
  return monthRegex.test(month);
};

export const isValidDay = (day: string): boolean => {
  const dayRegex: RegExp = /^([1-9]|0[1-9]|[1-2][0-9]|3[0-1])$/;
  return dayRegex.test(day);
};

export const isValidPhoneNumber = (phoneNumber: string): boolean => {
  const phoneRegex: RegExp = /^\d{11}$/;
  return phoneRegex.test(phoneNumber);
};

export const isValidEmail = (email: string): boolean => {
  const emailRegex: RegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  return emailRegex.test(email);
};

export const isValidPassword = (password: string): boolean => {
  const passwordRegex: RegExp =
    /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{6,12}$/;
  return passwordRegex.test(password);
};
