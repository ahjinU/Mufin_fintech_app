import useFetch from '@/hooks/useFetch';

type dataType = {
  data?: any;
  api: string;
};

export const usePostLoanApply = async (data: dataType) => {
  const { UsePostFetch } = useFetch();

  console.log(data);
  const res = await UsePostFetch(data);
  console.log(res);
  return res.json();
};
