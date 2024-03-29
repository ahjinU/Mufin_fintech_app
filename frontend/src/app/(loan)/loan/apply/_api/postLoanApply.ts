import useFetch from '@/hooks/useFetch';

type dataType = {
  data?: any;
  api: string;
};

export const usePostLoanApply = async (data: dataType) => {
  const { postFetch } = useFetch();

  console.log(data);
  const res = await postFetch(data);
  console.log(res);
  return res.json();
};
