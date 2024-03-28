const API_URL = `${process.env.NEXT_PUBLIC_BASE_URL}/api/user/signup`;

export const checkTelephoneParent = async (telephone: string) => {
  const res = await fetch(`${API_URL}/parent/check/telephone`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ telephone }),
  });
  return res;
};

export const checkTelephoneChild = async (
  AccessToken: string,
  telephone: string,
) => {
  const res = await fetch(`${API_URL}/child/check/telephone`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authrization: AccessToken,
    },
    body: JSON.stringify({ telephone }),
  });
  return res;
};

export const checkEmailParent = async (email: string) => {
  const res = await fetch(`${API_URL}/parent/check/email`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    credentials: 'include',
    body: JSON.stringify({ email }),
  });
  return res;
};

export const checkEmailChild = async (AccessToken: string, email: string) => {
  const res = await fetch(`${API_URL}/child/check/email`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authrization: AccessToken,
    },
    credentials: 'include',
    body: JSON.stringify({ email }),
  });
  return res;
};

export const signUpParent = async (
  name: string,
  gender: string,
  birth: string,
  address: string,
  address2: string,
  password: string,
) => {
  const res = await fetch(`${API_URL}/parent`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    credentials: 'include',
    body: JSON.stringify({ name, gender, birth, address, address2, password }),
  });
  return res;
};

export const signUpChild = async (
  AccessToken: string,
  name: string,
  gender: string,
  birth: string,
  address: string,
  address2: string,
  password: string,
) => {
  const res = await fetch(`${API_URL}/child`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authrization: AccessToken,
    },
    credentials: 'include',
    body: JSON.stringify({ name, gender, birth, address, address2, password }),
  });
  return res;
};
