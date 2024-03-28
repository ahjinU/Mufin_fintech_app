import { postFetch } from '@/hooks/useFetch';

type dataType = {
  data?: any;
  api: string;
};

export const postLoanApply = async (data: dataType) => {
  console.log(data);
  const res = await postFetch(data);
  console.log(res);
  return res.json();
};
