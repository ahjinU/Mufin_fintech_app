export type UserInfoProps = {
  name: string;
  gender: string;
  birth: string;
};

export type UserInfoPageProps = {
  onPrevious: () => void;
  onNext: () => void;
  userInfo: {};
};
