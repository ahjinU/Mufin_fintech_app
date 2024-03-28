const accessToken = `eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYXN1bWluIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTcxMTYxNTAyN30.dAPdoP-hemWzRCB8bpNlVpUXC6ypPIs6hy4hp1FNh3I`;

type dataType = {
  data?: any;
  api: string;
};

const postFetch = async (data: dataType) => {
  const res = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}${data.api}`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${accessToken}`,
    },
    body: JSON.stringify(data?.data),
  });
  return res.json();
};

const getFetch = async (data: dataType) => {
  const res = await fetch(`${process.env.NEXT_PUBLIC_BASE_URL}/${data.api}`, {
    method: 'GET',
    headers: {
      'Content-Type': 'application/json',
      Authorization: `Bearer ${accessToken}`,
    },
    body: JSON.stringify(data?.data),
  });
  return res.json();
};

export { getFetch, postFetch };
